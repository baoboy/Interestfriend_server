package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Video;

public interface VideoDao {
	int insertVidoeToDB(Video video);

	ResultSet getVideosByCid(int cid);
}
