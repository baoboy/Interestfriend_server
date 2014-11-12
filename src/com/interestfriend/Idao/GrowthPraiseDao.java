package com.interestfriend.Idao;

import com.interestfriend.bean.GrowthPraise;

public interface GrowthPraiseDao {
	boolean insertPraiseToDB(GrowthPraise praise);

	boolean findPraiseByUserID();// 查找用户是否已赞
}
