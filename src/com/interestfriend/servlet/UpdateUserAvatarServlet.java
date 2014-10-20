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

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.User;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.UserDaoFactory;

public class UpdateUserAvatarServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdateUserAvatarServlet() {
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

		request.setCharacterEncoding("utf-8");
		User user = new User();
		// 获得磁盘文件条目工厂。
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件上传需要保存的路径，user-avatar文件夹需存在。
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/user-avatar/";
		String avatarSavePath = request.getSession().getServletContext()
				.getRealPath("/user-avatar")
				+ File.separator;
		// 设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
		factory.setRepository(new File(avatarSavePath));
		// 设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
		factory.setSizeThreshold(1024 * 1024);
		// 上传处理工具类（高水平API上传处理？）
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
				if (item.isFormField()) {
					// 获取表单属性名字。
					String name = item.getFieldName();
					// 获取用户具体输入的字符串，
					String value = item.getString();
					// System.out.println(value);
					request.setAttribute(name, value);
					System.out.println(name + "  " + value);
				}
				// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
				else {
					// 获取路径名
					String value = item.getName();
					// 取到最后一个反斜杠。
					// int start = value.lastIndexOf("\\");
					// 截取上传文件的 字符串名字。+1是去掉反斜杠。
					// String filename = value.substring(start + 1);
					String filename = DateUtils.getUpLoadFileName()
							+ value.substring(value.length() - 4,
									value.length());
					// request.setAttribute(name, filename);
					/*
					 * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
					 */
					// 收到写到接收的文件中。
					OutputStream out = new FileOutputStream(new File(
							avatarSavePath, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("获取文件总量的容量:" + item.getSize());
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					item.delete();
					user.setUserAvatar(serverPath + filename);
				}
			}
			String user_id = request.getAttribute("user_id").toString();
			user.setUserLastUpdateTime(DateUtils.getLastUpdateTime());
			user.setUserID(Integer.valueOf(user_id));
			user.setUserState("UPDATE");
			UserDao dao = UserDaoFactory.getUserDaoInstance();
			boolean isSuccess = dao.updateUserAvatar(user);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!isSuccess) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				params.put("user_avatar", user.getUserAvatar());
				params.put("rt", 1);
			}
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			System.out.println(JsonUtil.toJsonString(params));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

	}
}
