package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.XinQingPraiseDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.XinQingPraise;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.XinQingPraiseDaoFactory;

public class CancelXinQingPraiseServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CancelXinQingPraiseServlet() {
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
		Map<String, Object> params = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int xinqing_id = Integer.valueOf(request.getParameter("xinqing_id"));
		XinQingPraiseDao dao = XinQingPraiseDaoFactory.getInstance();
		XinQingPraise praise = new XinQingPraise();
		praise.setXinqing_id(xinqing_id);
		praise.setUser_id(user_id);
		boolean ret = dao.cancelPraise(praise);
		if (!ret) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		} else {
			params.put("rt", 1);
			int praise_count = dao.getXinQingPraiseCount(xinqing_id);
			params.put("praise_count", praise_count);
		}
		out.print(JsonUtil.toJsonString(params));
		out.flush();
		out.close();
		System.out.println(JsonUtil.toJsonString(params));
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
