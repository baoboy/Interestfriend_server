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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into user_firend_invite_message(user_id,to_user_chat_id) values(?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, message.getUser_id());// ���õڶ�������
			pstmt.setString(2, message.getTo_user_chat_id());
			int count = pstmt.executeUpdate(); // ִ�и���
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
			// DBConnection.close(conn); // �ر����Ӷ���
		}
		return false;
	}

	@Override
	public boolean delMessage(UserFriendInviteMessage message) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "delete from user_firend_invite_message where user_id=? and to_user_chat_id=?";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, message.getUser_id());
			pstmt.setString(2, message.getTo_user_chat_id());
			return pstmt.executeUpdate() > 0; // ִ�и���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
			// DBConnection.close(conn); // �ر����Ӷ���
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
