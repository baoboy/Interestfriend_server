package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Circle;
import com.interestfriend.bean.Members;
import com.interestfriend.enums.CircleStatus;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.factory.UserDaoFactory;

public class UpdateCircleDiscirptionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateCircleDiscirptionServlet() {
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
		String circle_description = request.getParameter("circle_description");
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		Circle circle = new Circle();
		circle.setCircle_description(circle_description);
		circle.setCircle_id(circle_id);
		CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		boolean isSuccess = dao.updateCircleDiscreption(circle);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!isSuccess) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("err", 1);
			Members member = new Members();
			member.setCircle_id(circle_id);
			member.setCircle_state(CircleStatus.UPDATE.name());
			long lastReqTime = DateUtils.getLastUpdateTime();
			member.setCircle_last_request_time(lastReqTime);
			MembersDao mdao = MembersDaoFactory.getInstance();
			mdao.updateCircleLastRequestTimeAndState(member);
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
