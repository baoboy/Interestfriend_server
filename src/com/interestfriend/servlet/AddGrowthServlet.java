package com.interestfriend.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.ImageUtil;
import com.interestfriend.bean.Growth;
import com.interestfriend.bean.GrowthImage;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.GrowthDaoFactory;
import com.interestfriend.factory.GrowthImageDaoFactory;
import com.interestfriend.huanxin.EasemobSendMessage;

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
		String growthImageSavePath = request.getSession().getServletContext()
				.getRealPath("/growth-image")
				+ File.separator;

		factory.setRepository(new File(growthImageSavePath));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		int imgIndex = 1;
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
							+ "-"
							+ imgIndex
							+ value.substring(value.length() - 4,
									value.length());
					imgIndex++;
					GrowthImage image = new GrowthImage();
					image.setImg_url(serverPath + fileName);
					growthImages.add(image);
					// request.setAttribute(name, filename);
					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(
							growthImageSavePath, fileName));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					item.delete();
					// ImageUtil.resize(new File(avatarSavePath + fileName),
					// new File(avatarSavePath + path_200), 200, 0.7f);
				}
			}
			int cid = Integer.valueOf(request.getAttribute("cid").toString());
			String publisher_id = request.getAttribute("user_id").toString();
			String content = request.getAttribute("content").toString();
			// String time = request.getAttribute("time").toString();
			String time = DateUtils.getGrowthShowTime();
			Growth growth = new Growth();
			growth.setCid(cid);
			growth.setContent(content);
			growth.setTime(time);
			growth.setPublisher_id(Integer.valueOf(publisher_id));
			GrowthDao growthDao = GrowthDaoFactory.getGrowthDaoInstance();
			int growth_id = growthDao.insertGrowthToDB(growth);
			for (GrowthImage img : growthImages) {
				img.setCid(cid);
				img.setGrowth_id(growth_id);
			}
			GrowthImageDao imgDao = GrowthImageDaoFactory
					.getGrowthImageDaoInstance();
			imgDao.insertGrowthImageToDB(growthImages);
			Map<String, Object> params = new HashMap<String, Object>();
			if (growth_id > 0) {
				params.put("rt", 1);
				params.put("gid", growth_id);
				params.put("cid", cid);
				params.put("images", growthImages);
				params.put("time", time);

			} else {
				params.put("rt", 0);
				params.put("err", ErrorEnum.INVALID.name());
			}
			JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
			System.out.println(jsonObjectFromMap.toString());
			PrintWriter out = response.getWriter();
			out.print(jsonObjectFromMap.toString());
			out.flush();
			out.close();
			if (growth_id > 0) {
				CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
				String group_id = dao.getGroupIdByCircleID(cid);
				EasemobSendMessage.sendGroupMessage(group_id, publisher_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
