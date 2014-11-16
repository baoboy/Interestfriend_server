package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Growth;

public interface GrowthDao {
	int insertGrowthToDB(Growth growth);

	ResultSet getGrowthByCid(int cid, int refushState, String refushTime);

	int getGorwthPraiseCount(int growth_id);// ��ȡ�޵�����

	boolean updateGrowthPraiseCount(int growth_id, int praise_count);// �����޵�����

	int getGorwthCommentCount(int growth_id);// ��ȡ��������

	boolean updateGrowthCommentCount(int growth_id, int praise_count);// ������������

	String getUserHuanXinNameByGrowthID(int growth_id, int growth_publisher_id);

	Growth getGrowthByGrowthIDGrowth(int cid, int growth_id, int user_id);

	boolean updateGrowthUpdateTime(int growth_id);// �޻������ۺ� ����ʱ��
}
