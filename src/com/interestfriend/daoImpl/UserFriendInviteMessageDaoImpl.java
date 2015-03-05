package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.UserFriendInviteMessageDao;
import com.interestfriend.bean.UserFriendInviteMessage;
import com.interestfriend.db.DBConnection;

public class UserFriendInviteMessageDaoImpl implements
		UserFriendInviteMessageDao {

	@Override
	public boolean addMessage(UserFriendInviteMessage message) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into user_firend_invite_message(user_id,to_user_chat_id) values(?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, message.getUser_id());// 设置第二个参数
			pstmt.setString(2, message.getTo_user_chat_id());
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

	@Override
	public boolean delMessage(UserFriendInviteMessage message) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "delete from user_firend_invite_message where user_id=? and to_user_chat_id=?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, message.getUser_id());
			pstmt.setString(2, message.getTo_user_chat_id());
			return pstmt.executeUpdate() > 0; // 执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			// DBConnection.close(conn); // 关闭连接对象
		}
		return false;
	}

	@Override
	public boolean getMessage(UserFriendInviteMessage message) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select  * from user_firend_invite_message where user_id=? and to_user_chat_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message.getUser_id());
			pstmt.setString(2, message.getTo_user_chat_id());
			res = pstmt.executeQuery();
			while (res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return false;
	}

}
