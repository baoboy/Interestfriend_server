package com.interestfriend.factory;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.daoImpl.CircleDaoImpl;

public class CircleDaoFactory {
	public static CircleDao getCircleDaoInstance() {
		return new CircleDaoImpl();

	}
}
