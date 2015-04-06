package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.XinQingPraise;

public interface XinQingPraiseDao {
	boolean insertPraiseToDB(XinQingPraise praise);// ��

	boolean cancelPraise(XinQingPraise praise);// ȡ����

	int findPraiseByUserID(XinQingPraise praise);// �����û��Ƿ�����

	List<XinQingPraise> findPraiseUserByXinQingID(int xinqing_id);// ���Ҹóɳ����޵��û��б�

	int getXinQingPraiseCount(int xinqing_id);// ��ȡ�޵�����

}
