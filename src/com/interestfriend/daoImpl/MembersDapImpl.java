package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.PinYinUtil;
import com.interestfriend.bean.Members;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.CircleStatus;

public class MembersDapImpl implements MembersDao {

	@Override
	public boolean addMembers(Members member) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		// String addSQL =
		// "insert into circlemembers(user_id,circle_id,add_time) values(?,?,?)";
		String addSQL = "insert into circlemembers(user_id,circle_id,last_update_time,user_state,circle_last_request_time,circle_state) values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, member.getUser_id());
			pstmt.setInt(2, member.getCircle_id());// 设置第二个参数
			pstmt.setLong(3, member.getUser_update_time());
			pstmt.setString(4, "ADD");
			pstmt.setLong(5, member.getCircle_last_request_time());
			pstmt.setString(6, "ADD");
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
	public ResultSet findCirclesByUserID(int userID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL语句
		String findByIDSQL = "select circle.*, circlemembers.circle_state,user.user_name from( circle inner join circlemembers on circle.circle_id=circlemembers.circle_id AND circlemembers.user_id=? and  circlemembers.circle_last_request_time>?)inner join user on  circle.creator_id= user.user_id ";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, userID); // 设置参数
			pstmt.setLong(2, lastReqTime);

			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
		} finally {

		}
		return rs;
	}

	@Override
	public ResultSet findMembersByCircleID(int circleID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		// String findByIDSQL =
		// "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and `user`.user_last_update_time>? or circlemembers.add_time>?";

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL语句
		String findByIDSQL = "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and  circlemembers.last_update_time>?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circleID); // 设置参数
			pstmt.setLong(2, lastReqTime);
			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			// DBConnection.close(rs); // 关闭结果集对象
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	//
	// @Override
	// public boolean kickOutMemaber(Members member) {
	// Connection conn = DBConnection.getConnection(); // 获得连接对象
	// String addSQL =
	// "delete  from  circlemembers where user_id=? and circle_id=? ";
	// PreparedStatement pstmt = null; // 声明预处理对象
	// try {
	// pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
	// pstmt.setInt(1, member.getUser_id());
	// pstmt.setInt(2, member.getCircle_id());// 设置第二个参数
	// int count = pstmt.executeUpdate(); // 执行更新
	// return count > 0;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// DBConnection.close(pstmt); // 关闭预处理对象
	// }
	// return false;
	// }

	@Override
	public boolean kickOutMemaber(Members member) {
		String sql = "UPDATE circlemembers SET  last_update_time ="
				+ member.getUser_update_time() + ",user_state= '"
				+ member.getUser_state() + "' WHERE user_id ="
				+ member.getUser_id() + " and circle_id ="
				+ member.getCircle_id();
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			int res = pstmt.executeUpdate(); // 执行查询
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public boolean updateMemberLastUpdateTimeAndState(Members member) {
		String sql = "";
		if ("DEL".equals(member.getUser_state())) {
			sql = "UPDATE circlemembers SET  last_update_time ="
					+ DateUtils.getLastUpdateTime()
					+ ",user_state= 'DEL' WHERE user_id ="
					+ member.getUser_id() + " and circle_id="
					+ member.getCircle_id();
		} else {
			sql = "UPDATE circlemembers SET  last_update_time ="
					+ DateUtils.getLastUpdateTime() + ",user_state= '"
					+ member.getUser_state() + "' WHERE user_id ="
					+ member.getUser_id();
		}
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			int res = pstmt.executeUpdate(); // 执行查询
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public boolean updateCircleLastRequestTimeAndState(Members member) {
		String sql = "UPDATE circlemembers SET  circle_last_request_time ="
				+ member.getCircle_last_request_time() + ",circle_state= '"
				+ member.getCircle_state() + "' WHERE circle_id ="
				+ member.getCircle_id();
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			int res = pstmt.executeUpdate(); // 执行查询
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public CircleStatus findCircleStatus(int circle_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String findByIDSQL = "select circle_state from circlemembers where circle_id=? ";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circle_id); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return CircleStatus.convert(rs.getString("circle_state"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
		}
		return CircleStatus.INVALID;
	}

	@Override
	public int getCircleMemberNumOfCircle(int circle_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String findByIDSQL = "select user_id from  circlemembers where circle_id=?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circle_id); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
			rs.last(); // 移到最后一行
			int rowCount = rs.getRow(); // 得到当前行号，也就是记录数
			System.out.println("member_count:" + rowCount);
			return rowCount;
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt);
		}
		return 1;
	}
}
