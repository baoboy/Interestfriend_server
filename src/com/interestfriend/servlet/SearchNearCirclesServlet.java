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

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.CategoryCircleUtils;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.CircleStatus;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;

public class SearchNearCirclesServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchNearCirclesServlet() {
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
		int page = Integer.valueOf(request.getParameter("page"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		double longitude = Double.valueOf(request.getParameter("longitude"));
		double latitude = Double.valueOf(request.getParameter("latitude")
				.toString());
		System.out.println(longitude + "     " + latitude);
		CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		ResultSet res = dao.findCirclesByLongitudeAndLatitude(longitude,
				latitude, page);
		MembersDao mDao = MembersDaoFactory.getInstance();
		List<Circle> circleLists = new ArrayList<Circle>();
		Map<String, Object> params = new HashMap<String, Object>();
		if (res != null) {
			try {
				while (res.next()) {
					int creator_id = res.getInt("creator_id");
					if (creator_id == user_id) {
						continue;
					}
					Circle circle = new Circle();
					circle.setCircle_avatar(res.getString("circle_avatar"));
					circle.setCircle_description(res
							.getString("circle_description"));
					int circle_id = res.getInt("circle_id");
					CircleStatus status = mDao.findCircleStatus(circle_id);
					if (status == CircleStatus.DEL) {
						continue;
					}
					int circle_member_count = mDao
							.getCircleMemberNumOfCircle(circle_id);
					if (circle_member_count >= 1500) {
						continue;
					}
					circle.setCircle_member_num(circle_member_count);
					circle.setCircle_id(circle_id);
					circle.setCircle_name(res.getString("circle_name"));
					circle.setGroup_id(res.getString("group_id"));
					circle.setDistance((int) Utils.getDistanceOfMeter(latitude,
							longitude, res.getDouble("latitude"),
							res.getDouble("longitude")));
					circle.setCreator_id(creator_id);
					circle.setCircle_create_time(res
							.getString("circle_create_time"));
					circle.setCircle_creator_name(res.getString("user_name"));
					int category = res.getInt("category");
					circle.setCircle_category(CategoryCircleUtils
							.getCateGoryNameByCode(category));
					circleLists.add(circle);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.toString());
			} finally {
				DBConnection.close(res);
			}
			params.put("circles", circleLists);
			params.put("rt", 1);
		} else {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		PrintWriter out = response.getWriter();
		out.print(jsonObjectFromMap.toString());
		out.flush();
		out.close();
		System.out.println(jsonObjectFromMap.toString());
		dao.updateCircleLastRequestTime(user_id);// ���²鿴����Ȧ��ʱ��
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
