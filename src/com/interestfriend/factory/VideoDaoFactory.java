package com.interestfriend.factory;

import com.interestfriend.Idao.VideoDao;
import com.interestfriend.daoImpl.VideoDaoImpl;

public class VideoDaoFactory {
	public static VideoDao getInstances() {
		return new VideoDaoImpl();

	}
}
