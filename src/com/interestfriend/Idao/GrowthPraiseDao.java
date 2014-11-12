package com.interestfriend.Idao;

import com.interestfriend.bean.GrowthPraise;

public interface GrowthPraiseDao {
	boolean insertPraiseToDB(GrowthPraise praise);// 赞

	boolean cancelPraise(GrowthPraise praise);// 取消赞

	int findPraiseByUserID(GrowthPraise praise);// 查找用户是否已赞
}
