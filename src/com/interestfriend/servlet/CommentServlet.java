package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Comment;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CommentDaoFactory;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.huanxin.EasemobSendMessage;

public class CommentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommentServlet() {
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
		int growth_id = Integer.valueOf(request.getParameter("growth_id"));
		String publisher_id = request.getParameter("user_id");
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		String user_name = request.getParameter("user_name");
		int growth_publisher_id = Integer.valueOf(request
				.getParameter("growth_publisher_id"));
		Comment comment = new Comment();
		comment.setComment_content(comment_content);
		comment.setReply_someone_id(Integer.valueOf(reply_someone_id));
		comment.setReply_someone_name(reply_someone_name);
		comment.setComment_time(DateUtils.getGrowthShowTime());
		comment.setGrowth_id(growth_id);
		comment.setPublisher_id(Integer.valueOf(publisher_id));
		CommentDao dao = CommentDaoFactory.getInstances();
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
			GrowthDao gDao = GrowthDaoFactory.getGrowthDaoInstance();
			boolean res = gDao.updateGrowthUpdateTime(growth_id);
			System.out.println(res);
			String growth_publisher_huanxin_name = gDao
					.getUserHuanXinNameByGrowthID(growth_id,
							growth_publisher_id);
			if (!"".equals(reply_someone_name)) {
				if (Integer.valueOf(reply_someone_id) == Integer
						.valueOf(publisher_id)) {
					return;
				}
				growth_publisher_huanxin_name = gDao
						.getUserHuanXinNameByGrowthID(growth_id,
								Integer.valueOf(reply_someone_id));
				EasemobSendMessage.sendTextMessageForpRraiseAndComment(
						circle_id, growth_id, growth_publisher_huanxin_name,
						"'" + user_name + "‘ 回复了您的评论");
			}
			if (growth_publisher_id == Integer.valueOf(publisher_id)) {
				return;
			}
			EasemobSendMessage.sendTextMessageForpRraiseAndComment(circle_id,
					growth_id, growth_publisher_huanxin_name, "'" + user_name
							+ "‘ 评论了您的动态");
			System.out.println("reply:::::::" + reply_someone_id + "  "
					+ publisher_id);

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
