package com.interestfriend.Idao;

import com.interestfriend.bean.User;

public interface UserDao {
	boolean insertUserToDB(User user);// 向数据库里插入一条用户数据

	boolean verifyCellphone(String cellPhone);// 验证手机号是否存在
}
