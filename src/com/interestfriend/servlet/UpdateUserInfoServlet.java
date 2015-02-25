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
import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Members;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.factory.UserDaoFactory;

public class UpdateUserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateUserInfoServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf8");
		String cloumn = request.getParameter("cloumn");
		String value = request.getParameter("value").replace("'", "");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		UserDao dao = UserDaoFactory.getUserDaoInstance();
		if ("�ǳ�".equals(cloumn)) {
			String user_pinyin = request.getParameter("user_pinyin");
			String user_sort_key = request.getParameter("user_sort_key");
			boolean res = dao.updateUserName(user_id, value, user_sort_key,
					user_pinyin);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!res) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				params.put("err", 1);
				MembersDao mDao = MembersDaoFactory.getInstance();
				Members member = new Members();
				member.setUser_id(Integer.valueOf(user_id));
				member.setUser_state("UPDATE");
				mDao.updateMemberLastUpdateTimeAndState(member);
			}
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			return;
		}
		boolean isSuccess = dao.updateUserInfo(user_id, cloumn, value);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!isSuccess) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("err", 1);
			MembersDao mDao = MembersDaoFactory.getInstance();
			Members member = new Members();
			member.setUser_id(Integer.valueOf(user_id));
			member.setUser_state("UPDATE");
			mDao.updateMemberLastUpdateTimeAndState(member);
		}
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.toJsonString(params));
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
