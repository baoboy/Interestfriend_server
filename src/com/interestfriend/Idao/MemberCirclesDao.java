package com.interestfriend.Idao;

import java.sql.ResultSet;

public interface MemberCirclesDao {
	ResultSet findCirclesByUserID(int userID, long lastReqTime);// ��ȡĳ���û���Ȧ���б�

	boolean delMemberCircleByCircleID(int circle_id);

}
