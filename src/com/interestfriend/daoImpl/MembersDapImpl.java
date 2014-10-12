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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into circlemembers(user_id,circle_id) values(?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, member.getUser_id());
			pstmt.setInt(2, member.getCircle_id());// ���õڶ�������
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
	public ResultSet findCirclesByUserID(int userID) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL���
		String findByIDSQL = "select * from circle inner join circlemembers on circle.circle_id=circlemembers.circle_id AND circlemembers.user_id=?";
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, userID); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
		} finally {
			// DBConnection.close(rs); // �رս��������
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	@Override
	public ResultSet findMembersByCircleID(int circleID, long lastReqTime) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		// String findByIDSQL = "select * from circle where user_id = ?"; //
		// SQL���
		String findByIDSQL = "select * from user inner join circlemembers on `user`.user_id=circlemembers.user_id AND circlemembers.circle_id=? and `user`.user_last_update_time>"
				+ lastReqTime;
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circleID); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
		} finally {
			// DBConnection.close(rs); // �رս��������
			// DBConnection.close(pstmt);
		}
		return rs;
	}
}
