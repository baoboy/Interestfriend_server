package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	int insertCircleToDB(Circle circle);// ����һ��Ȧ����Ϣ�����ݿ�

	ResultSet findCirclesByUserID(int userID);

	ResultSet findCirclesByCategory(int category, int page);// ��������Ȧ��

	String getGroupIdByCircleID(int circle_id);// ��ȡ����Ⱥ��id

	ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude, int page); // ����γ�Ȳ��Ҹ���Ȧ��

	boolean updateCircleDiscreption(Circle circle); // �޸�Ȧ������

	boolean updateCircleLastRequestTime(int creator_id);

	boolean updateCircleLongitudeAndLatitude(double longitude, double latitude,
			int creator_id);
}
