package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.bean.Members;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.huanxin.EasemobGroupMessage;
import com.interestfriend.huanxin.EasemobSendMessage;

public class ReceiveJoinCircleRequestServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReceiveJoinCircleRequestServlet() {
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
		int user_id = Integer.valueOf(request
				.getParameter("request_join_circle_user_id"));
		int circle_id = Integer.valueOf(request.getParameter("join_circle_id"));
		String group_id = request.getParameter("group_id");
		String huanxin_userName = request
				.getParameter("request_join_circle_user_huanxin_username");
		String join_circle_name = request.getParameter("join_circle_name");
		long lastReqTime = DateUtils.getLastUpdateTime();
		Members member = new Members();
		member.setCircle_id(circle_id);
		member.setUser_id(user_id);
		member.setUser_update_time(lastReqTime);
		member.setCircle_last_request_time(lastReqTime);
		MembersDao dao = MembersDaoFactory.getInstance();
		boolean rt = dao.addMembers(member);
		EasemobGroupMessage.addUserToGroup(group_id, huanxin_userName);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!rt) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			params.put("circle_last_request_time", lastReqTime);
		}
		PrintWriter out = response.getWriter();
		out.print(params);
		out.flush();
		out.close();
		EasemobSendMessage.sendMessageForReceiveJoinCircle("管理员已经同意您加入  ‘"
				+ join_circle_name + "' 圈子", huanxin_userName);
		System.out.println("---" + huanxin_userName);
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
