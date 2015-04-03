package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.XinQing;

public interface XinQingDao {
	int insertToDB(XinQing xinqing);

	List<XinQing> getXinQingList(int refushState, String refushTime);

}
