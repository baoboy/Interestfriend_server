package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.UserFriendDao;
import com.interestfriend.bean.UserFriend;
import com.interestfriend.db.DBConnection;

public class UserFriendDaoImpl implements UserFriendDao {

	@Override
	public boolean addUserFriend(UserFriend user) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into user_friend(user_id,user_friend_id,user_friend_name,user_friend_avatar,user_friend_chat_id,user_friend_circle) values(?,?,?,?,?,?)";
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

	@Override
	public List<UserFriend> getUserFriendList(int user_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select   user_friend_id,user_friend_name,user_friend_avatar,user_friend_chat_id,user_friend_circle from user_friend where user_id=?";
		List<UserFriend> lists = new ArrayList<UserFriend>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				UserFriend user = new UserFriend();
				user.setUser_friend_avatar(res.getString("user_friend_avatar"));
				user.setUser_friend_chat_id(res
						.getString("user_friend_chat_id"));
				user.setUser_friend_circle(res.getString("user_friend_circle"));
				user.setUser_friend_name(res.getString("user_friend_name"));
				user.setUser_friend_id(res.getInt("user_friend_id"));
				lists.add(user);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return null;
	}

	@Override
	public boolean delFriendByUserId(int user_id, int user_friend_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "delete from user_friend where user_id=? and user_friend_id=?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, user_friend_id);
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
	public boolean getUser(UserFriend user) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select  * from user_friend where user_id=? and user_friend_chat_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUser_id());
			pstmt.setString(2, user.getUser_friend_chat_id());
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
