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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		// String addSQL =
		// "insert into circlemembers(user_id,circle_id,add_time) values(?,?,?)";
		String addSQL = "insert into circlemembers(user_id,circle_id,last_update_time,user_state,circle_last_request_time,circle_state) values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, member.getUser_id());
			pstmt.setInt(2, member.getCircle_id());// ���õڶ�������
			pstmt.setLong(3, member.getUser_update_time());
			pstmt.setString(4, "ADD");
			pstmt.setLong(5, member.getCircle_last_request_time());
			pstmt.setString(6, "ADD");
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
	public ResultSet findCirclesByUserID(int userID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL���
		String findByIDSQL = "select circle.*, circlemembers.circle_state,user.user_name from( circle inner join circlemembers on circle.circle_id=circlemembers.circle_id AND circlemembers.user_id=? and  circlemembers.circle_last_request_time>?)inner join user on  circle.creator_id= user.user_id ";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, userID); // ���ò���
			pstmt.setLong(2, lastReqTime);

			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
		} finally {

		}
		return rs;
	}

	@Override
	public ResultSet findMembersByCircleID(int circleID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		// String findByIDSQL =
		// "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and `user`.user_last_update_time>? or circlemembers.add_time>?";

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL���
		String findByIDSQL = "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and  circlemembers.last_update_time>?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circleID); // ���ò���
			pstmt.setLong(2, lastReqTime);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			// DBConnection.close(rs); // �رս��������
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	//
	// @Override
	// public boolean kickOutMemaber(Members member) {
	// Connection conn = DBConnection.getConnection(); // ������Ӷ���
	// String addSQL =
	// "delete  from  circlemembers where user_id=? and circle_id=? ";
	// PreparedStatement pstmt = null; // ����Ԥ�������
	// try {
	// pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
	// pstmt.setInt(1, member.getUser_id());
	// pstmt.setInt(2, member.getCircle_id());// ���õڶ�������
	// int count = pstmt.executeUpdate(); // ִ�и���
	// return count > 0;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// DBConnection.close(pstmt); // �ر�Ԥ�������
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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public boolean updateCircleLastRequestTimeAndState(Members member) {
		String sql = "UPDATE circlemembers SET  circle_last_request_time ="
				+ member.getCircle_last_request_time() + ",circle_state= '"
				+ member.getCircle_state() + "' WHERE circle_id ="
				+ member.getCircle_id();
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public CircleStatus findCircleStatus(int circle_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select circle_state from circlemembers where circle_id=? ";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle_id); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select user_id from  circlemembers where circle_id=?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle_id); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			rs.last(); // �Ƶ����һ��
			int rowCount = rs.getRow(); // �õ���ǰ�кţ�Ҳ���Ǽ�¼��
			System.out.println("member_count:" + rowCount);
			return rowCount;
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt);
		}
		return 1;
	}
}
