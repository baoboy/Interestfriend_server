package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	int insertCircleToDB(Circle circle);// ����һ��Ȧ����Ϣ�����ݿ�

	ResultSet findCirclesByUserID(int userID);

	ResultSet findCirclesByCategory(int category);// ��������Ȧ��

}
