package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.UserFriendDao;
import com.interestfriend.bean.UserFriend;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.UserFriendDaoFactory;

public class AddUserFriendServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUserFriendServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_name = request.getParameter("user_name");
		String user_avatar = request.getParameter("user_avatar");
		String user_chat_id = request.getParameter("user_chat_id");
		int user_friend_id = Integer.valueOf(request
				.getParameter("user_friend_id"));
		String user_friend_name = request.getParameter("user_friend_name");
		String user_friend_avatar = request.getParameter("user_friend_avatar");
		String user_friend_chat_id = request
				.getParameter("user_friend_chat_id");
		String user_friend_circle = request.getParameter("user_friend_circle");
		UserFriendDao dao = UserFriendDaoFactory.getInstance();
		// 把对方加入到自己的好友列表里
		UserFriend user = new UserFriend();
		user.setUser_id(user_id);
		user.setUser_friend_avatar(user_friend_avatar);
		user.setUser_friend_chat_id(user_friend_chat_id);
		user.setUser_friend_circle(user_friend_circle);
		user.setUser_friend_id(user_friend_id);
		user.setUser_friend_name(user_friend_name);
		dao.addUserFriend(user);
		// 把自己加入到对方的好有列表里
		user = new UserFriend();
		user.setUser_id(user_friend_id);
		user.setUser_friend_avatar(user_avatar);
		user.setUser_friend_chat_id(user_chat_id);
		user.setUser_friend_circle(user_friend_circle);
		user.setUser_friend_id(user_id);
		user.setUser_friend_name(user_name);
		boolean result = dao.addUserFriend(user);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!result) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
		}
		PrintWriter out = response.getWriter();
		out.print(params);
		out.flush();
		out.close();
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
