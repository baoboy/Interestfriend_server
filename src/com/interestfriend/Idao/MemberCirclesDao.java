package com.interestfriend.Idao;

import java.sql.ResultSet;

public interface MemberCirclesDao {
	ResultSet findCirclesByUserID(int userID, long lastReqTime);// 获取某个用户的圈子列表

	boolean delMemberCircleByCircleID(int circle_id);

}
