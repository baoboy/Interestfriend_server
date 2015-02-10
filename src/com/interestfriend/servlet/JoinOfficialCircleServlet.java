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

public class JoinOfficialCircleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JoinOfficialCircleServlet() {
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
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_name = request.getParameter("user_name");
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		String circle_name = request.getParameter("circle_name");
		int circle_creator = Integer.valueOf(request
				.getParameter("circle_creator"));
		String group_id = request.getParameter("group_id");
		String huanxin_userName = request.getParameter("huanxin_username");//
		MembersDao dao = MembersDaoFactory.getInstance();
		boolean res = dao.findMemberInCircle(circle_id, user_id);
		Map<String, Object> params = new HashMap<String, Object>();
		if (res) {
			params.put("err", ErrorEnum.ALERADY_IN_CIRCLE.name());
			params.put("rt", 0);
			PrintWriter out = response.getWriter();
			out.print(params);
			out.flush();
			out.close();
			return;
		}
		// if (circle_id < 0) {
		long lastReqTime = DateUtils.getLastUpdateTime();
		Members member = new Members();
		member.setCircle_id(circle_id);
		member.setUser_id(user_id);
		member.setUser_update_time(lastReqTime);
		member.setCircle_last_request_time(lastReqTime);
		boolean rt = dao.addMembers(member);
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
		EasemobGroupMessage.addUserToGroup(group_id, huanxin_userName);
		EasemobSendMessage.sendNotifyForSomeOneJoinCircle(group_id, user_id,
				user_name);
		// }
		// UserDao uDao = UserDaoFactory.getUserDaoInstance();
		// String user_chat_id = uDao.findUserChatIDByUserID(circle_creator);
		// EasemobSendMessage.sendMessageForJoinCircle("'" + user_name + "'"
		// + " 请求加入您创建的   ’" + circle_name + "‘  圈子", user_chat_id,
		// circle_id, user_id, group_id, huanxin_userName, circle_name);
		// params.put("rt", 1);
		// params.put("circle_last_request_time",
		// DateUtils.getLastUpdateTime());
		// PrintWriter out = response.getWriter();
		// out.print(params);
		// out.flush();
		// out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
	}

}
