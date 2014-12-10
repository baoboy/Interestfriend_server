package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.GrowthPraiseDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.huanxin.EasemobSendMessage;

public class GrowthPraiseServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GrowthPraiseServlet() {
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
		int growth_id = Integer.valueOf(request.getParameter("growth_id"));
		int growth_publisher_id = Integer.valueOf(request
				.getParameter("growth_publisher_id"));
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		String user_name = request.getParameter("user_name");
		GrowthPraiseDao dao = GrowthPraiseDaoFactory.getInstance();
		GrowthPraise praise = new GrowthPraise();
		praise.setGrowth_id(growth_id);
		praise.setUser_id(user_id);
		boolean ret = dao.insertPraiseToDB(praise);
		if (!ret) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			return;
		}
		GrowthDao gDao = GrowthDaoFactory.getGrowthDaoInstance();
		int praise_count = gDao.getGorwthPraiseCount(growth_id);
		ret = gDao.updateGrowthPraiseCount(growth_id, praise_count + 1);
		if (!ret) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		} else {
			params.put("rt", 1);
			params.put("praise_count", praise_count + 1);
		}
		out.print(JsonUtil.toJsonString(params));
		out.flush();
		out.close();
		System.out.println(JsonUtil.toJsonString(params));
		if (ret) {
			boolean res = gDao.updateGrowthUpdateTime(growth_id);
			System.out.println(res);
			if (growth_publisher_id == user_id) {
				return;
			}
			MembersDao mdao = MembersDaoFactory.getInstance();
			res = mdao.findMemberInCircle(circle_id,
					Integer.valueOf(growth_publisher_id));
			if (!res) {
				return;
			}
			String growth_publisher_huanxin_name = gDao
					.getUserHuanXinNameByGrowthID(growth_id,
							growth_publisher_id);
			EasemobSendMessage.sendTextMessageForpRraiseAndComment(circle_id,
					growth_id, growth_publisher_huanxin_name, "'" + user_name
							+ "¡® ÔÞÁËÄúµÄ¶¯Ì¬");

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
