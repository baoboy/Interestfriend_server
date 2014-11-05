package com.interestfriend.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.VideoDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.GrowthImage;
import com.interestfriend.bean.Video;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.VideoDaoFactory;
import com.interestfriend.huanxin.EasemobUserAPI;

public class UpLoadVideoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpLoadVideoServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		Map<String, Object> params = new HashMap<String, Object>();
		PrintWriter outP = response.getWriter();
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/video/";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String videoSavePath = request.getSession().getServletContext()
				.getRealPath("/video")
				+ File.separator;
		String response_video_path = "";
		String video_img_path = "";
		factory.setRepository(new File(videoSavePath));
		factory.setSizeThreshold(1024 * 1024 * 10);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
				if (item.isFormField()) {
					// 获取表单属性名字。
					String name = item.getFieldName();
					// 获取用户具体输入的字符串，
					String value = item.getString();
					request.setAttribute(name, value);
				}
				// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
				else {
					// 获取路径名
					String value = item.getName();
					System.out.println("path:" + value);
					String fileName = DateUtils.getUpLoadFileName()
							+ value.substring(value.lastIndexOf("."));
					if (fileName.endsWith(".jpg")) {
						video_img_path = serverPath + fileName;
						System.out.println(video_img_path);
					} else {
						response_video_path = serverPath + fileName;
					}

					// request.setAttribute(name, filename);
					/*
					 * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
					 */
					// 收到写到接收的文件中。
					OutputStream out = new FileOutputStream(new File(
							videoSavePath, fileName));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					item.delete();

				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
			outP.print(JsonUtil.toJsonString(params));
			outP.flush();
			outP.close();
			return;
		}
		int cid = Integer.valueOf(request.getAttribute("cid").toString());
		int publisher_id = Integer.valueOf(request.getAttribute("user_id")
				.toString());
		Video video = new Video();
		video.setCid(cid);
		video.setPublisher_id(publisher_id);
		video.setVideo_img(video_img_path);
		video.setVideo_path(response_video_path);
		video.setVideo_duration(Integer.valueOf(request.getAttribute(
				"video_duration").toString()));
		video.setVideo_size(Integer.valueOf(request.getAttribute("video_size")
				.toString()));
		video.setVideo_content(request.getAttribute("video_content").toString());
		video.setTime(request.getAttribute("time").toString());
		VideoDao dao = VideoDaoFactory.getInstances();
		dao.insertVidoeToDB(video);
		params.put("rt", 1);
		params.put("video_path", response_video_path);
		params.put("video_img_path", video_img_path);
		outP.print(JsonUtil.toJsonString(params));
		outP.flush();
		outP.close();
		System.out.println(JsonUtil.toJsonString(params));
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
