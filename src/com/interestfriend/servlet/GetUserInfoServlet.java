package com.interestfriend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.User;
import com.interestfriend.factory.UserDaoFactory;

public class GetUserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetUserInfoServlet() {
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
		UserDao dao = UserDaoFactory.getUserDaoInstance();
		ResultSet res = dao.getUserInfo(user_id);
		String user_name = "";
		String user_avatar = "";
		User u = new User();

		try {
			while (res.next()) {
				// user_avatar = res.getString("user_avatar");
				// user_name = res.getString("user_name");
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
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		// params.put("user_name", user_name);
		// params.put("user_avatar", user_avatar);
		params.put("user", u);
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.listToJsonArray("user", u));
		JSONObject jsonObject = JSONObject.fromObject(params);
		out.flush();
		out.close();
		System.out.println(jsonObject.toString());
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
