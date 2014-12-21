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

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Members;
import com.interestfriend.enums.CircleStatus;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;

public class UpdateCircleLogoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdateCircleLogoServlet() {
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
		String circle_logo = "";
		request.setCharacterEncoding("utf-8");
		// ��ô����ļ���Ŀ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ȡ�ļ��ϴ���Ҫ�����·����user-avatar�ļ�������ڡ�
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/circle_images/";
		String avatarSavePath = request.getSession().getServletContext()
				.getRealPath("/circle_images/")
				+ File.separator;
		// ������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�
		factory.setRepository(new File(avatarSavePath));
		// ���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�
		factory.setSizeThreshold(1024 * 1024);
		// �ϴ��������ࣨ��ˮƽAPI�ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// ���� parseRequest��request������ ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���
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
					System.out.println(name + "  " + value);
				}
				// ���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���
				else {
					// ��ȡ·����
					String value = item.getName();
					// ȡ�����һ����б�ܡ�
					// int start = value.lastIndexOf("\\");
					// ��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�
					// String filename = value.substring(start + 1);
					String filename = DateUtils.getUpLoadFileName()
							+ value.substring(value.length() - 4,
									value.length());
					// request.setAttribute(name, filename);
					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(
							avatarSavePath, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("��ȡ�ļ�����������:" + item.getSize());
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					item.delete();
					circle_logo = serverPath + filename;
				}
			}
			String circle_id = request.getAttribute("circle_id").toString();
			CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
			boolean isSuccess = dao.updateCircleLogo(
					Integer.valueOf(circle_id), circle_logo);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!isSuccess) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				params.put("circle_logo", circle_logo);
				params.put("rt", 1);
				Members member = new Members();
				member.setCircle_id(Integer.valueOf(circle_id));
				member.setCircle_state(CircleStatus.UPDATE.name());
				long lastReqTime = DateUtils.getLastUpdateTime();
				member.setCircle_last_request_time(lastReqTime);
				MembersDao mdao = MembersDaoFactory.getInstance();
				mdao.updateCircleLastRequestTimeAndState(member);
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
