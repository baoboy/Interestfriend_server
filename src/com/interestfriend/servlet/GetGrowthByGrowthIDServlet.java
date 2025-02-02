package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Growth;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CommentDaoFactory;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.GrowthImageDaoFactory;
import com.interestfriend.factory.GrowthPraiseDaoFactory;

public class GetGrowthByGrowthIDServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetGrowthByGrowthIDServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int circle_id = Integer.valueOf(request.getParameter("circle_id"));
		int growth_id = Integer.valueOf(request.getParameter("growth_id"));
		System.out.println("id:::::::" + user_id + "   " + circle_id + "   "
				+ growth_id);
		Map<String, Object> params = new HashMap<String, Object>();
		GrowthDao dao = GrowthDaoFactory.getGrowthDaoInstance();
		Growth growth = dao.getGrowthByGrowthIDGrowth(circle_id, growth_id,
				user_id);
		List<Growth> lists = new ArrayList<Growth>();
		lists.add(growth);
		params.put("growths", growth);
		params.put("cid", circle_id);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		PrintWriter out = response.getWriter();
		out.print(jsonObjectFromMap.toString());
		out.flush();
		out.close();
		System.out.println(jsonObjectFromMap.toString());
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
