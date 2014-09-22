package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	boolean insertCircleToDB(Circle circle);// 插入一条圈子信息到数据库

	ResultSet findCirclesByUserID(int userID);
}
