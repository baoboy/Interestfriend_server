package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	boolean insertCircleToDB(Circle circle);// ����һ��Ȧ����Ϣ�����ݿ�

	ResultSet findCirclesByUserID(int userID);
}
