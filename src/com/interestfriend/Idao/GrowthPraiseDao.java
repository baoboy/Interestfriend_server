package com.interestfriend.Idao;

import com.interestfriend.bean.GrowthPraise;

public interface GrowthPraiseDao {
	boolean insertPraiseToDB(GrowthPraise praise);// ��

	boolean cancelPraise(GrowthPraise praise);// ȡ����

	int findPraiseByUserID(GrowthPraise praise);// �����û��Ƿ�����
}
