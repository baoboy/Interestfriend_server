package com.interestfriend.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.XinQingDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.bean.XinQing;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.XinQingDaoFactory;

public class AddXinQingServlet extends HttpServlet {
	String xinqingImageSavePath = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		xinqingImageSavePath = this.getServletConfig().getServletContext()
				.getRealPath("/xinqing_images")
				+ File.separator;
	}

	/**
	 * Constructor of the object.
	 */
	public AddXinQingServlet() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String image_url = "";
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/xinqing_images/";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(xinqingImageSavePath + "/temp"));
		factory.setSizeThreshold(4096);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置允许上传的最大文件大小 10M
		upload.setSizeMax(10 * 1024 * 1024);
		int imgIndex = 1;
		try {
			// 解析HTTP请求消息头
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
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
					String fileName = DateUtils.getUpLoadFileName()
							+ "-"
							+ imgIndex
							+ value.substring(value.length() - 4,
									value.length());
					imgIndex++;
					image_url = serverPath + fileName;
					File file = new File(xinqingImageSavePath, fileName);
					item.write(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("img:" + e.toString());
		}
		String publisher_id = request.getAttribute("user_id").toString();
		String content = request.getAttribute("content").toString();
		String publish_time = DateUtils.getGrowthShowTime();
		XinQing xinqing = new XinQing();
		xinqing.setContent(content);
		xinqing.setImage_url(image_url);
		xinqing.setPublish_time(publish_time);
		xinqing.setPublisher_id(Integer.valueOf(publisher_id));
		XinQingDao dao = XinQingDaoFactory.getInstance();
		int id = dao.insertToDB(xinqing);
		Map<String, Object> params = new HashMap<String, Object>();
		if (id > 0) {
			params.put("rt", 1);
			params.put("xinqing_id", id);
			params.put("image_url", image_url);
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
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
