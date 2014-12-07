package com.interestfriend.Utils;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.bean.Circle;
import com.interestfriend.factory.CircleDaoFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		for (int i = 1; i < 80; i++) {
			Circle circle = new Circle();
			circle.setCreator_id(-1);
			circle.setCategory(1);
			circle.setLatitude(39.9160140);
			circle.setLongitude(116.4659610);
			circle.setCircle_name("��" + i + "��Ȧ��");
			dao.insertCircleToDB(circle);
		}
		System.out.println("finish");
	}
}
