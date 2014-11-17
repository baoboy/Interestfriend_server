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

import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.CategoryCircleUtils;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Circle;
import com.interestfriend.daoImpl.MembersDapImpl;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;

public class CircleListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CircleListServlet() {
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
		response.setContentType("text/html; charset=utf8");
		request.setCharacterEncoding("utf8");

		int user_id = Integer.valueOf(request.getParameter("user_id"));
		long lastReqTime = Long.valueOf(request
				.getParameter("circle_last_request_time"));
		System.out.println("lastTIme:" + lastReqTime);
		// CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		MembersDao dao = MembersDaoFactory.getInstance();
		ResultSet res = dao.findCirclesByUserID(user_id, lastReqTime);
		List<Circle> circleLists = new ArrayList<Circle>();
		try {
			while (res.next()) {
				Circle circle = new Circle();
				circle.setCircle_avatar(res.getString("circle_avatar"));
				circle.setCircle_description(res
						.getString("circle_description"));
				int circle_id = res.getInt("circle_id");
				circle.setCircle_id(circle_id);
				circle.setCircle_name(res.getString("circle_name"));
				circle.setGroup_id(res.getString("group_id"));
				circle.setCreator_id(res.getInt("creator_id"));
				circle.setCircle_state(res.getString("circle_state"));
				circle.setCircle_create_time(res
						.getString("circle_create_time"));
				circle.setCircle_creator_name(res.getString("user_name"));
				int category = res.getInt("category");
				circle.setCircle_category(CategoryCircleUtils
						.getCateGoryNameByCode(category));
				circle.setCircle_member_num(dao
						.getCircleMemberNumOfCircle(circle_id));
				circleLists.add(circle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			DBConnection.close(res);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("circles", circleLists);
		params.put("rt", 1);
		params.put("circle_last_request_time", DateUtils.getLastUpdateTime());

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
	}

}
