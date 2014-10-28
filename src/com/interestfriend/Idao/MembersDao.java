package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Members;

public interface MembersDao {
	boolean addMembers(Members member);

	ResultSet findCirclesByUserID(int userID);

	ResultSet findMembersByCircleID(int circleID, long lastReqTime);

	boolean kickOutMemaber(Members member);

	boolean updateMemberLastUpdateTimeAndState(Members member);
}
