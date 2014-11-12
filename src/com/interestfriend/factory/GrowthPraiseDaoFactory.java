package com.interestfriend.factory;

import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.daoImpl.GrowthPraiseDaoImpl;

public class GrowthPraiseDaoFactory {
	public static GrowthPraiseDao getInstance() {
		return new GrowthPraiseDaoImpl();
	}
}
