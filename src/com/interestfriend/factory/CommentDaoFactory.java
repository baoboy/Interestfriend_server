package com.interestfriend.factory;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.daoImpl.CommentDaoImpl;

public class CommentDaoFactory {
	public static CommentDao getInstances() {
		return new CommentDaoImpl();

	}
}
