package com.interestfriend.factory;

import com.interestfriend.Idao.SMSCodeDao;
import com.interestfriend.daoImpl.SMSCodeDaoImpl;

public class SMSCodeDaoFactory {
	public static SMSCodeDao getinstance() {
		return new SMSCodeDaoImpl();
	}
}
