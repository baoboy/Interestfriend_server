package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.User;

public interface UserDao {
	boolean insertUserToDB(User user);// 向数据库里插入一条用户数据

	boolean verifyCellphone(String cellPhone);// 验证手机号是否存在

	int userLogon(String telPhone, String password);

	ResultSet getUserInfo(int user_id);
}
