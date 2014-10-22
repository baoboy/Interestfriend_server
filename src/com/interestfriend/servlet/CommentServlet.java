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
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Comment;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CommentDaoFactory;

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
		String growth_id = request.getParameter("growth_id");
		String publisher_id = request.getParameter("user_id");
		Comment comment = new Comment();
		comment.setComment_content(comment_content);
		comment.setComment_time(DateUtils.getGrowthShowTime());
		comment.setGrowth_id(Integer.valueOf(growth_id));
		comment.setPublisher_id(Integer.valueOf(publisher_id));
		CommentDao dao = CommentDaoFactory.getInstances();
		boolean res = dao.insertComment(comment);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!res) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
		}
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.toJsonString(params));
		out.flush();
		out.close();
		System.out.println(params.toString());
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
