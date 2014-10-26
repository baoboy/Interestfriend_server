package com.interestfriend.factory;

import com.interestfriend.Idao.VideoCommentDao;
import com.interestfriend.daoImpl.VideoCommentImpl;

public class VideoCommentFactory {
	public static VideoCommentDao getIntances() {
		return new VideoCommentImpl();

	}
}
