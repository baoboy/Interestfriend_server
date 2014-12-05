package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Circle;

public interface CircleDao {
	int insertCircleToDB(Circle circle);// 插入一条圈子信息到数据库

	ResultSet findCirclesByUserID(int userID);

	ResultSet findCirclesByCategory(int category, int page);// 按类别查找圈子

	String getGroupIdByCircleID(int circle_id);// 获取聊天群组id

	ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude, int page); // 按经纬度查找附近圈子

	boolean updateCircleDiscreption(Circle circle); // 修改圈子描述

	boolean updateCircleLastRequestTime(int creator_id);

	boolean updateCircleLongitudeAndLatitude(double longitude, double latitude,
			int creator_id);
}
