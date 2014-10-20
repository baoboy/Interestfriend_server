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

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.MembersDaoFactory;

public class GetMemberCirclesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GetMemberCirclesServlet() {
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

		int user_id = Integer.valueOf(request.getParameter("member_id"));
		// CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		MembersDao dao = MembersDaoFactory.getInstance();
		ResultSet res = dao.findCirclesByUserID(user_id);
		List<Circle> circleLists = new ArrayList<Circle>();
		try {
			while (res.next()) {
				Circle circle = new Circle();
				circle.setCircle_avatar(res.getString("circle_avatar"));
				circle.setCircle_description(res
						.getString("circle_description"));
				circle.setCircle_id(res.getInt("circle_id"));
				circle.setCircle_name(res.getString("circle_name"));
				circle.setGroup_id(res.getString("group_id"));
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
