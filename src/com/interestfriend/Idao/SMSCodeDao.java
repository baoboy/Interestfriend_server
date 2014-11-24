package com.interestfriend.Idao;

import com.interestfriend.bean.SMSCode;

public interface SMSCodeDao {
	boolean insertToDB(SMSCode code);

	String findCodeByCellphone(String user_cellphone);

	boolean delCodeByUserCellPhone(String user_cellphone);// É¾³ýÑéÖ¤Âë
}
