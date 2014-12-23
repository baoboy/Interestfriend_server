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
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.SMSCode;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.SMSCodeDaoFactory;
import com.interestfriend.factory.UserDaoFactory;
import com.interestfriend.smscode.RestSMSCode;

public class VerifyCellPhoneServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public VerifyCellPhoneServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf8");
		String cellphone = request.getParameter("user_cellphone");
		UserDao dao = UserDaoFactory.getUserDaoInstance();
		boolean isExist = dao.verifyCellphone(cellphone);
		Map<String, Object> params = new HashMap<String, Object>();
		if (isExist) {
			params.put("err", ErrorEnum.USER_ALREADY_EXIST.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
		}
		PrintWriter out = response.getWriter();
		System.out.println(JsonUtil.toJsonString(params));
		out.print(JsonUtil.toJsonString(params));
		out.flush();
		out.close();
		if (!isExist) {
			SMSCode code = new SMSCode();
			String str_code = Utils.getSMSCode();
			code.setSms_code(str_code);
			code.setUser_cellphone(cellphone);
			code.setTime(DateUtils.getUpLoadFileName());
			SMSCodeDao c_dao = SMSCodeDaoFactory.getinstance();
			c_dao.delCodeByUserCellPhone(cellphone);
			c_dao.insertToDB(code);
			RestSMSCode.sendCode(str_code, cellphone);
			System.out.println("sms_code:" + str_code);
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
