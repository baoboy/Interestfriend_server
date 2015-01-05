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
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.UserDaoFactory;

public class UserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
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
		String user_cellphone = request.getParameter("user_cellphone");
		String user_passwrod = request.getParameter("user_password");
		System.out.println("user:" + user_cellphone + "        "
				+ user_passwrod);
		UserDao dao = UserDaoFactory.getUserDaoInstance();
		int user_id = dao.userLogon(user_cellphone, user_passwrod);
		ResultSet res = dao.getUserInfo(user_id);
		User u = new User();
		try {
			while (res.next()) {
				u.setUserID(res.getInt("user_id"));
				u.setUserName(res.getString("user_name"));
				u.setUserAvatar(res.getString("user_avatar"));
				u.setUserBirthday(res.getString("user_birthday"));
				u.setUserGender(res.getString("user_gender"));
				u.setUserRegisterTime(res.getString("user_register_time"));
				u.setUserChatId(res.getString("user_cellphone"));
				u.setPinYinFir(res.getString("user_pinyin_str"));
				u.setSortKey(res.getString("user_sort_key"));
				u.setUserDeclaration(res.getString("user_declaration"));
				u.setUserDescription(res.getString("user_description"));
				u.setUserChatId(res.getString("user_chat_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (user_id == -1) {
			params.put("err", ErrorEnum.NOT_EXIST_USER.name());
			params.put("rt", 0);
		} else if (user_id == -2) {
			params.put("err", ErrorEnum.WRONG_PASSWORD.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			params.put("user_id", user_id);
			params.put("user", u);
		}
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = JSONObject.fromObject(params);
		out.print(jsonObject.toString());
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
