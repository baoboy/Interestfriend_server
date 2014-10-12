package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.bean.Members;
import com.interestfriend.db.DBConnection;

public class MembersDapImpl implements MembersDao {

	@Override
	public boolean addMembers(Members member) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into circlemembers(user_id,circle_id) values(?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, member.getUser_id());
			pstmt.setInt(2, member.getCircle_id());// 设置第二个参数
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
	public ResultSet findCirclesByUserID(int userID) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL语句
		String findByIDSQL = "select * from circle inner join circlemembers on circle.circle_id=circlemembers.circle_id AND circlemembers.user_id=?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, userID); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
		} finally {
			// DBConnection.close(rs); // 关闭结果集对象
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	@Override
	public ResultSet findMembersByCircleID(int circleID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL语句
		String findByIDSQL = "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and `user`.user_last_update_time>"
				+ lastReqTime;
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circleID); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
		} finally {
			// DBConnection.close(rs); // 关闭结果集对象
			// DBConnection.close(pstmt);
		}
		return rs;
	}
}
