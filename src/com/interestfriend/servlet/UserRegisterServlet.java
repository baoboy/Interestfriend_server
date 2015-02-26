package com.interestfriend.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.MD5;
import com.interestfriend.bean.User;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.UserDaoFactory;
import com.interestfriend.huanxinImpl.EasemobIMUsers;

public class UserRegisterServlet extends HttpServlet {
	String avatarSavePath = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		avatarSavePath = this.getServletConfig().getServletContext()
				.getRealPath("/user-avatar")
				+ File.separator;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserRegisterServlet() {
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
		out.println(", using the GET methodimggggggggggg");
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = new User();
		// ��ô����ļ���Ŀ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ȡ�ļ��ϴ���Ҫ�����·����user-avatar�ļ�������ڡ�
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/user-avatar/";
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
					File file = new File(avatarSavePath, filename);
					item.write(file);
					item.delete();
					user.setUserAvatar(serverPath + filename);
				}
			}
			String user_nameString = request.getAttribute("user_name")
					.toString();
			String user_cellphone = request.getAttribute("user_cellphone")
					.toString();
			String user_password = request.getAttribute("user_password")
					.toString();
			String user_gender = request.getAttribute("user_gender").toString();
			String user_birthday = request.getAttribute("user_birthday")
					.toString();
			String user_pinyin = request.getAttribute("user_pinyin").toString();
			String user_sort_key = request.getAttribute("user_sort_key")
					.toString();
			String user_address = request.getAttribute("user_address")
					.toString();
			String user_province = request.getAttribute("user_province")
					.toString();
			String user_province_key = request
					.getAttribute("user_province_key").toString();
			user.setUserBirthday(user_birthday);
			user.setUserCellPhone(user_cellphone);
			user.setUserGender(user_gender);
			user.setUserName(user_nameString);
			user.setUserPassword(user_password);
			user.setUserRegisterTime(DateUtils.getRegisterTime());
			user.setUserLastUpdateTime(DateUtils.getLastUpdateTime());
			user.setPinYinFir(user_pinyin);
			user.setSortKey(user_sort_key);
			user.setUserState("add");
			user.setUserChatId(MD5.Md5(user_cellphone));
			user.setUserAddress(user_address);
			user.setUserProvince(user_province);
			user.setUserProvinceKey(user_province_key);
			UserDao dao = UserDaoFactory.getUserDaoInstance();
			boolean isSuccess = dao.insertUserToDB(user);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!isSuccess) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				// EasemobUserAPI.createNewUser(MD5.Md5(user_cellphone),
				// MD5.Md5(user_password));
				// EasemobUserAPI.createNewUser(user_cellphone, user_password);
				EasemobIMUsers.createNewUser(MD5.Md5(user_cellphone),
						MD5.Md5(user_cellphone));
				params.put("rt", 1);
			}
			PrintWriter out = response.getWriter();
			out.print(params);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("upload_pic:" + e.toString());
		}

	}
}
