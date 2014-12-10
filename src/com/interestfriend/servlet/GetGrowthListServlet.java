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
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.Growth;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CommentDaoFactory;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.GrowthImageDaoFactory;
import com.interestfriend.factory.GrowthPraiseDaoFactory;

public class GetGrowthListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetGrowthListServlet() {
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
		int cid = Integer.valueOf(request.getParameter("cid"));
		int refushState = Integer.valueOf(request.getParameter("refushState"));
		String refushTime = request.getParameter("refushTime");
		Utils.print("refushTime::::::::" + refushTime);
		GrowthDao dao = GrowthDaoFactory.getGrowthDaoInstance();
		ResultSet res = dao.getGrowthByCid(cid, refushState, refushTime);
		List<Growth> lists = new ArrayList<Growth>();
		GrowthImageDao imgDao = GrowthImageDaoFactory
				.getGrowthImageDaoInstance();
		CommentDao coDao = CommentDaoFactory.getInstances();
		Map<String, Object> params = new HashMap<String, Object>();
		GrowthPraise praise = new GrowthPraise();
		praise.setUser_id(user_id);
		GrowthPraiseDao gDao = GrowthPraiseDaoFactory.getInstance();
		try {
			while (res.next()) {
				Growth g = new Growth();
				int growth_id = res.getInt("growth_id");
				praise.setGrowth_id(growth_id);
				g.setIsPraise(gDao.findPraiseByUserID(praise));
				g.setGrowth_id(growth_id);
				g.setContent(res.getString("content"));
				g.setTime(res.getString("time"));
				int publisher_id = res.getInt("publisher_id");
				g.setPublisher_id(publisher_id);
				g.setImages(imgDao.getImagesByGrowthID(cid, growth_id));
				g.setComments(coDao.getCommentByGrowthID(growth_id));
				g.setPublisher_avatar(res.getString("user_avatar"));
				g.setPublisher_name(res.getString("user_name"));
				g.setPraise_count(res.getInt("praise_count"));
				g.setPraises(gDao.findPraiseUserByGrowthID(growth_id));
				g.setState(res.getString("state"));
				g.setLast_update_time(res.getString("last_update_time"));
				lists.add(g);
			}
			params.put("growths", lists);
			params.put("cid", cid);
			params.put("lastReqTime", DateUtils.getGrowthShowTime());
			params.put("rt", 1);
		} catch (SQLException e) {
			e.printStackTrace();
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		} finally {
			DBConnection.close(res);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println(jsonObjectFromMap.toString());
		PrintWriter out = response.getWriter();
		out.print(jsonObjectFromMap.toString());
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
