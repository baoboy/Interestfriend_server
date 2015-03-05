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
import com.interestfriend.Idao.UserFriendInviteMessageDao;
import com.interestfriend.bean.UserFriend;
import com.interestfriend.bean.UserFriendInviteMessage;
import com.interestfriend.factory.UserFriendDaoFactory;
import com.interestfriend.factory.UserFriendInviteMessageDaoFactory;
import com.interestfriend.huanxinImpl.EasemobMessages;

/**
 * ∑¢ÀÕÃÌº”∫√”—…Í«Î
 * 
 * @author songbinbin
 * 
 */
public class AddUserFriendInviteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUserFriendInviteServlet() {
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
		String to_user_chat_id = request.getParameter("to_user_chat_id");
		String reason = request.getParameter("reason");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_name = request.getParameter("user_name");
		String user_avatar = request.getParameter("user_avatar");
		String from_circle = request.getParameter("from_circle");
		UserFriendInviteMessage message = new UserFriendInviteMessage();
		message.setTo_user_chat_id(to_user_chat_id);
		message.setUser_id(user_id);
		UserFriendInviteMessageDao dao = UserFriendInviteMessageDaoFactory
				.getInstance();
		boolean result = dao.getMessage(message);
		UserFriendDao userDao = UserFriendDaoFactory.getInstance();
		UserFriend user = new UserFriend();
		user.setUser_id(user_id);
		user.setUser_friend_chat_id(to_user_chat_id);
		boolean userResult = userDao.getUser(user);
		if (!result && !userResult) {
			EasemobMessages.addUserFriendInvite(to_user_chat_id, reason,
					user_id, user_name, user_avatar, from_circle);
			dao.addMessage(message);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
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
