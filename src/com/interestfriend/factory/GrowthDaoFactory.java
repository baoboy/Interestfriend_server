package com.interestfriend.factory;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.daoImpl.GrowthDaoImpl;

public class GrowthDaoFactory {
	public static GrowthDao getGrowthDaoInstance() {
		return new GrowthDaoImpl();

	}

}
