package com.interestfriend.Utils;

import java.util.ArrayList;
import java.util.List;

import com.interestfriend.bean.CategoryCircle;

public class CategoryCircleUtils {
	public static List<CategoryCircle> lists = new ArrayList<CategoryCircle>();

	static {
		CategoryCircle c = new CategoryCircle();
		c.setCode(1);
		c.setName("�ٷ�Ȧ��");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(2);
		c.setName("��Ϸ����");
		c.setLogo("http://192.168.1.101:8080/InterestFriend/images/category/category_game.png");
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
	}

	public static String getCateGoryNameByCode(int code) {
		for (CategoryCircle cate : lists) {
			if (cate.getCode() == code) {
				return cate.getName();
			}
		}
		return "����Ȧ��";
	}
}
