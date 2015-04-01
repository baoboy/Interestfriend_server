package com.interestfriend.factory;

import com.interestfriend.Idao.XinQingDao;
import com.interestfriend.daoImpl.XinQingDaoImpl;

public class XinQingDaoFactory {
	public static XinQingDao getInstance() {
		return new XinQingDaoImpl();
	}
}
