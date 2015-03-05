package com.interestfriend.factory;

import com.interestfriend.Idao.UserFriendInviteMessageDao;
import com.interestfriend.daoImpl.UserFriendInviteMessageDaoImpl;

public class UserFriendInviteMessageDaoFactory {
	public static UserFriendInviteMessageDao getInstance() {
		return new UserFriendInviteMessageDaoImpl();

	}
}
