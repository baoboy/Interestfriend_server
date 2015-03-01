package com.interestfriend.factory;

import com.interestfriend.Idao.UserFriendDao;
import com.interestfriend.daoImpl.UserFriendDaoImpl;

public class UserFriendDaoFactory {
	public static UserFriendDao getInstance() {
		return new UserFriendDaoImpl();
	}
}
