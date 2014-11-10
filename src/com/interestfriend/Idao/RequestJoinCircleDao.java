package com.interestfriend.Idao;

import com.interestfriend.bean.RequestJoinCircle;

public interface RequestJoinCircleDao {
	boolean requestJoinCircle(RequestJoinCircle joinCircle);

	boolean receiveRequest();

	boolean refushRequest();
}
