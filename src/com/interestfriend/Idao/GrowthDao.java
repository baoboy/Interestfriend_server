package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Growth;

public interface GrowthDao {
	int insertGrowthToDB(Growth growth);

	/**
	 * 
	 * @param cid
	 * @param refushState
	 *            1 下拉刷新 2 加载更多
	 * @param refushTime
	 * @return
	 */
	ResultSet getGrowthByCid(int cid, int refushState, String refushTime);
}
