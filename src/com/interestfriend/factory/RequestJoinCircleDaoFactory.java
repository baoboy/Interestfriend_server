package com.interestfriend.factory;

import com.interestfriend.Idao.RequestJoinCircleDao;
import com.interestfriend.daoImpl.RequestJoinCircleDaoImpl;

public class RequestJoinCircleDaoFactory {
	public static RequestJoinCircleDao getInstance() {
		return new RequestJoinCircleDaoImpl();
	}
}
