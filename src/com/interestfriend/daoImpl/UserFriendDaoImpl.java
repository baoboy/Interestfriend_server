package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.interestfriend.Idao.UserFriendDao;
import com.interestfriend.bean.UserFriend;
import com.interestfriend.db.DBConnection;

public class UserFriendDaoImpl implements UserFriendDao {

	@Override
	public boolean addUserFriend(UserFriend user) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into USER(user_id,user_friend_id,user_friend_name,user_friend_avatar,user_friend_chat_id,user_friend_circle) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, user.getUser_id());// 设置第二个参数
			pstmt.setInt(2, user.getUser_friend_id());
			pstmt.setString(3, user.getUser_friend_name());
			pstmt.setString(4, user.getUser_friend_avatar());
			pstmt.setString(5, user.getUser_friend_chat_id());
			pstmt.setString(6, user.getUser_friend_circle());
			int count = pstmt.executeUpdate(); // 执行更新
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			// DBConnection.close(conn); // 关闭连接对象
		}
		return false;
	}

}
