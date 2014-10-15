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

import com.interestfriend.Idao.VideoDao;
import com.interestfriend.bean.Video;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.VideoDaoFactory;

public class GetVideoListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetVideoListServlet() {
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
		int cid = Integer.valueOf(request.getParameter("cid"));
		VideoDao dao = VideoDaoFactory.getInstances();
		ResultSet res = dao.getVideosByCid(cid);
		List<Video> lists = new ArrayList<Video>();
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			while (res.next()) {
				Video video = new Video();
				video.setPublisher_id(res.getInt("publisher_id"));
				video.setVideo_duration(res.getInt("video_duration"));
				video.setVideo_img(res.getString("video_img"));
				video.setVideo_path(res.getString("video_path"));
				video.setVideo_size(res.getInt("video_size"));
				video.setVideo_id(res.getInt("video_id"));
				lists.add(video);
			}
			params.put("videos", lists);
			params.put("cid", cid);
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
