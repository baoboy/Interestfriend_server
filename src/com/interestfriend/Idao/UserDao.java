package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.User;

public interface UserDao {
	boolean insertUserToDB(User user);// �����ݿ������һ���û�����

	boolean verifyCellphone(String cellPhone);// ��֤�ֻ����Ƿ����

	int userLogon(String telPhone, String password);

	ResultSet getUserInfo(int user_id);
}
