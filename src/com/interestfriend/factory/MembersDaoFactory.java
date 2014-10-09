package com.interestfriend.factory;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.daoImpl.MembersDapImpl;

public class MembersDaoFactory {
	public static MembersDao getInstance() {
		return new MembersDapImpl();
	}
}
