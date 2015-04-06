package com.interestfriend.factory;

import com.interestfriend.Idao.XinQingCommentDao;
import com.interestfriend.daoImpl.XinQingCommentDaoImpl;

public class XinQingCommentDaoFactory {

	public static XinQingCommentDao getInstance() {
		return new XinQingCommentDaoImpl();
	}
}