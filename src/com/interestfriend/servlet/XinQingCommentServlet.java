package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Idao.XinQingCommentDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.XinQingComment;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.factory.XinQingCommentDaoFactory;
import com.interestfriend.huanxinImpl.EasemobMessages;

public class XinQingCommentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public XinQingCommentServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String comment_content = request.getParameter("comment_content");
		String reply_someone_name = request.getParameter("reply_someone_name");
		String reply_someone_id = request.getParameter("reply_someone_id");
		int xinqing_id = Integer.valueOf(request.getParameter("xinqing_id"));
		String publisher_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		int xinqing_publisher_id = Integer.valueOf(request
				.getParameter("xinqing_publisher_id"));
		XinQingComment comment = new XinQingComment();
		comment.setComment_content(comment_content);
		comment.setReply_someone_id(Integer.valueOf(reply_someone_id));
		comment.setReply_someone_name(reply_someone_name);
		comment.setComment_time(DateUtils.getGrowthShowTime());
		comment.setXinqing_id(xinqing_id);
		comment.setPublisher_id(Integer.valueOf(publisher_id));
		XinQingCommentDao dao = XinQingCommentDaoFactory.getInstance();
		int id = dao.insertComment(comment);
		Map<String, Object> params = new HashMap<String, Object>();
		if (id < 0) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			params.put("comment_id", id);

		}
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.toJsonString(params));
		out.flush();
		out.close();
		if (id > 0) {
			MembersDao mDao = MembersDaoFactory.getInstance();
			String xinqing_publisher_huanxin_name = mDao
					.getHuanXinIDByUserID(xinqing_publisher_id);
			if (!"".equals(reply_someone_name)) {
				if (Integer.valueOf(reply_someone_id) == Integer
						.valueOf(publisher_id)) {
					return;
				}

				EasemobMessages.sendTextMessageForpXinQingRraiseAndComment(
						xinqing_id, xinqing_publisher_huanxin_name, "'"
								+ user_name + "‘ 回复了您的评论");
			}
			// if (xinqing_publisher_id == Integer.valueOf(publisher_id)) {
			// return;
			// }

			EasemobMessages.sendTextMessageForpXinQingRraiseAndComment(
					xinqing_id, xinqing_publisher_huanxin_name, "'" + user_name
							+ "‘ 评论了您的心情");

		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
