package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.GrowthPraise;

public interface GrowthPraiseDao {
	boolean insertPraiseToDB(GrowthPraise praise);// ��

	boolean cancelPraise(GrowthPraise praise);// ȡ����

	int findPraiseByUserID(GrowthPraise praise);// �����û��Ƿ�����

	List<GrowthPraise> findPraiseUserByGrowthID(int growth_id);// ���Ҹóɳ����޵��û��б�
}
