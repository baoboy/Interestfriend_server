package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Growth;

public interface GrowthDao {
	int insertGrowthToDB(Growth growth);

	ResultSet getGrowthByCid(int cid);
}
