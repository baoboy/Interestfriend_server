package com.interestfriend.factory;

import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.daoImpl.GrowthImageDaoImpl;

public class GrowthImageDaoFactory {
	public static GrowthImageDao getGrowthImageDaoInstance() {
		return new GrowthImageDaoImpl();

	}
}
