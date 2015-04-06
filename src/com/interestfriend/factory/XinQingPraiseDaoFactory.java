package com.interestfriend.factory;

import com.interestfriend.Idao.XinQingPraiseDao;
import com.interestfriend.daoImpl.XinQingPraiseDaoImpl;

public class XinQingPraiseDaoFactory {
	public static XinQingPraiseDao getInstance() {
		return new XinQingPraiseDaoImpl();
	}
}
