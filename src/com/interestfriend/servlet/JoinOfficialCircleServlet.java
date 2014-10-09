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
import com.interestfriend.bean.Members;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.MembersDaoFactory;

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
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		Members member = new Members();
		member.setCircle_id(circle_id);
		member.setUser_id(user_id);
		MembersDao dao = MembersDaoFactory.getInstance();
		boolean rt = dao.addMembers(member);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!rt) {
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
