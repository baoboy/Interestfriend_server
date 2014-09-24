package com.interestfriend.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.bean.Growth;
import com.interestfriend.bean.GrowthImage;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.GrowthImageDaoFactory;

public class AddGrowthServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public AddGrowthServlet() {
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
		List<GrowthImage> growthImages = new ArrayList<GrowthImage>();
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/growth-image/";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String avatarSavePath = request.getSession().getServletContext()
				.getRealPath("/growth-image")
				+ File.separator;

		factory.setRepository(new File(avatarSavePath));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// ���� parseRequest��request������ ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���
			@SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// �����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����
				if (item.isFormField()) {
					// ��ȡ���������֡�
					String name = item.getFieldName();
					// ��ȡ�û�����������ַ�����
					String value = item.getString();
					// System.out.println(value);
					request.setAttribute(name, value);
				}
				// ���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���
				else {
					// ��ȡ·����
					String value = item.getName();
					// ȡ�����һ����б�ܡ�
					int start = value.lastIndexOf("\\");
					// ��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�
					// String filename = value.substring(start + 1);
					String fileName = DateUtils.getUpLoadFileName()
							+ value.substring(value.length() - 4,
									value.length());
					GrowthImage image = new GrowthImage();
					image.setImg_url(serverPath + fileName);
					growthImages.add(image);
					System.out.println("image::::::::" + serverPath + fileName);
					// request.setAttribute(name, filename);
					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(
							avatarSavePath, fileName));
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
			String cid = request.getAttribute("cid").toString();
			String publisher_id = request.getAttribute("publisher_id")
					.toString();
			String content = request.getAttribute("content").toString();
			Growth growth = new Growth();
			growth.setCid(Integer.valueOf(cid));
			growth.setContent(content);
			growth.setPublisher_id(Integer.valueOf(publisher_id));

			GrowthDao growthDao = GrowthDaoFactory.getGrowthDaoInstance();
			growthDao.insertGrowthToDB(growth);
			GrowthImageDao imgDao = GrowthImageDaoFactory
					.getGrowthImageDaoInstance();
			imgDao.insertGrowthImageToDB(growthImages);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
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
