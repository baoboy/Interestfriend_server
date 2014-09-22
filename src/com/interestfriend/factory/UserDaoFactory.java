package com.interestfriend.factory;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.daoImpl.UserDaoImpl;

public class UserDaoFactory {
	public static UserDao getUserDaoInstance() {
		return new UserDaoImpl();
	}
}
