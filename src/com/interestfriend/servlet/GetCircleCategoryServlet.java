package com.interestfriend.servlet;

import java.io.IOException;
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

import com.interestfriend.bean.CategoryCircle;

public class GetCircleCategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCircleCategoryServlet() {
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
		List<CategoryCircle> lists = getCategory();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("catagory", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		PrintWriter out = response.getWriter();
		out.print(jsonObjectFromMap.toString());
		out.flush();
		out.close();
	}

	private List<CategoryCircle> getCategory() {
		List<CategoryCircle> lists = new ArrayList<CategoryCircle>();
		CategoryCircle c = new CategoryCircle();
		c.setCode(1);
		c.setName("�ٷ�Ȧ��");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(2);
		c.setName("��Ϸ����");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(3);
		c.setName("��ͨ����");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(4);
		c.setName("��������");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(5);
		c.setName("��Ӱ����");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(6);
		c.setName("�������");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(7);
		c.setName("�����˶�");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(8);
		c.setName("��ѧ����");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(9);
		c.setName("����Ȧ��");
		lists.add(c);
		return lists;

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
