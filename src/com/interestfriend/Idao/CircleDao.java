package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	int insertCircleToDB(Circle circle);// 插入一条圈子信息到数据库

	ResultSet findCirclesByUserID(int userID);

	ResultSet findCirclesByCategory(int category);// 按类别查找圈子

	String getGroupIdByCircleID();// 获取聊天群组id

	ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude); // 按经纬度查找附近圈子

}
