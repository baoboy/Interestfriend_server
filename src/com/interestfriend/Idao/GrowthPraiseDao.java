package com.interestfriend.Idao;

import com.interestfriend.bean.GrowthPraise;

public interface GrowthPraiseDao {
	boolean insertPraiseToDB(GrowthPraise praise);

	boolean findPraiseByUserID();// �����û��Ƿ�����
}
