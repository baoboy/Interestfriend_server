package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.SMSCodeDao;
import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.SMSCode;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.SMSCodeDaoFactory;
import com.interestfriend.factory.UserDaoFactory;

public class CheckVerifyCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckVerifyCodeServlet() {
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
		String cellphone = request.getParameter("user_cellphone");
		String sms_code = request.getParameter("sms_code");
		SMSCodeDao dao = SMSCodeDaoFactory.getinstance();
		String code = dao.findCodeByCellphone(cellphone);
		Utils.print("check_code:" + sms_code + "  " + code + "   " + cellphone);
		boolean res = code.equals(sms_code);
		Map<String, Object> params = new HashMap<String, Object>();
		if (!res) {
			params.put("err", ErrorEnum.SMS_CODE_ERR.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			dao.delCodeByUserCellPhone(cellphone);
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
