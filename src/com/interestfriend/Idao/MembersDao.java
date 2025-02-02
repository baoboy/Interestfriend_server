package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Members;
import com.interestfriend.enums.CircleStatus;
import com.interestfriend.enums.Status;

public interface MembersDao {
	boolean addMembers(Members member);

	ResultSet findCirclesByUserID(int userID, long lastReqTime);

	ResultSet findMembersByCircleID(int circleID, long lastReqTime);

	boolean kickOutMemaber(Members member);

	boolean updateMemberLastUpdateTimeAndState(Members member);

	boolean updateCircleLastRequestTimeAndState(Members member);

	CircleStatus findCircleStatus(int circle_id);

	Status findUserStateInCircle(int user_id, int cirlce_id);// 查找用户在圈子内的状态

	int getCircleMemberNumOfCircle(int circle_id);

	boolean findMemberInCircle(int circle_id, int user_id);// 该成员在圈子内是否存在

	String getHuanXinIDByUserID(int user_id);
}
