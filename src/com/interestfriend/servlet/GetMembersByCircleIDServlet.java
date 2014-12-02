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
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Circle;
import com.interestfriend.bean.User;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.CircleStatus;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.enums.Status;
import com.interestfriend.factory.MembersDaoFactory;

public class GetMembersByCircleIDServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetMembersByCircleIDServlet() {
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
		Map<String, Object> params = new HashMap<String, Object>();
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int cid = Integer.valueOf(request.getParameter("circle_id"));
		long lastReqTime = Long.valueOf(request.getParameter("lastReqTime"));

		System.out.println(cid + "       " + lastReqTime);
		MembersDao dao = MembersDaoFactory.getInstance();
		CircleStatus status = dao.findCircleStatus(cid);
		Status user_status = dao.findUserStateInCircle(user_id, cid);
		System.out.println("user_sate" + user_status);
		if (status == CircleStatus.DEL) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.CIRCLE_ALERADY_DISSOLVE.name());
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			return;
		}
		if (user_status == Status.DEL) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.KICKOUT_CIRCLE.name());
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			return;
		}
		ResultSet res = dao.findMembersByCircleID(cid, lastReqTime);
		List<User> userLists = new ArrayList<User>();
		try {
			while (res.next()) {
				User u = new User();
				u.setUserID(res.getInt("user_id"));
				u.setUserName(res.getString("user_name"));
				u.setUserAvatar(res.getString("user_avatar"));
				u.setUserBirthday(res.getString("user_birthday"));
				u.setUserGender(res.getString("user_gender"));
				u.setUserRegisterTime(res.getString("user_register_time"));
				u.setUserChatId(res.getString("user_cellphone"));
				u.setPinYinFir(res.getString("user_pinyin_str"));
				u.setSortKey(res.getString("user_sort_key"));
				u.setUserState(res.getString("user_state"));
				u.setUserDeclaration(res.getString("user_declaration"));
				u.setUserDescription(res.getString("user_description"));

				userLists.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			DBConnection.close(res);
		}
		params.put("members", userLists);
		params.put("rt", 1);
		params.put("lastReqTime", DateUtils.getLastUpdateTime());
		params.put("circle_id", cid);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		PrintWriter out = response.getWriter();
		out.print(jsonObjectFromMap.toString());
		System.out.println(jsonObjectFromMap.toString());
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
