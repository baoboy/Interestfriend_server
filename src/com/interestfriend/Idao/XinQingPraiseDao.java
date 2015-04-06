package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.XinQingPraise;

public interface XinQingPraiseDao {
	boolean insertPraiseToDB(XinQingPraise praise);// 赞

	boolean cancelPraise(XinQingPraise praise);// 取消赞

	int findPraiseByUserID(XinQingPraise praise);// 查找用户是否已赞

	List<XinQingPraise> findPraiseUserByXinQingID(int xinqing_id);// 查找该成长所赞的用户列表

	int getXinQingPraiseCount(int xinqing_id);// 获取赞的数量

}
