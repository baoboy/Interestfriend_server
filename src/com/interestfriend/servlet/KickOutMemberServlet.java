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
import com.interestfriend.huanxinImpl.EasemobChatGroups;
import com.interestfriend.huanxinImpl.EasemobMessages;

public class KickOutMemberServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public KickOutMemberServlet() {
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
		int user_id = Integer.valueOf(request.getParameter("kickout_user_id"));
		String kick_out_user_chat_id = request
				.getParameter("kickout_user_chat_id");
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		String group_id = request.getParameter("group_id");
		String circle_name = request.getParameter("circle_name");
		Members member = new Members();
		member.setCircle_id(circle_id);
		member.setUser_id(user_id);
		member.setCircle_state("DEL");
		member.setUser_state("DEL");
		long lastReqTime = DateUtils.getLastUpdateTime();
		member.setUser_update_time(lastReqTime);
		MembersDao dao = MembersDaoFactory.getInstance();
		boolean rt = dao.kickOutMemaber(member);
		// member.setCircle_last_request_time(lastReqTime);
		// dao.updateCircleLastRequestTimeAndState(member);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!rt) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			params.put("lastReqTime", lastReqTime);
		}
		PrintWriter out = response.getWriter();
		out.print(params);
		out.flush();
		out.close();
		if (rt) {
			EasemobMessages.sendMessageForKickOutCircle("您已经被管理员踢出  '"
					+ circle_name + "' 圈子", kick_out_user_chat_id, circle_id,
					user_id);
			EasemobChatGroups.deleteFromGroup(group_id, kick_out_user_chat_id);
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
