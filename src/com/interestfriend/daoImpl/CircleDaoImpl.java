package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;

public class CircleDaoImpl implements CircleDao {

	public boolean insertCircleToDB(Circle circle) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into circle(user_id,circle_name,circle_description,circle_avatar,group_id) values(?,?,?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle.getUser_id());
			pstmt.setString(2, circle.getCircle_name());// ���õڶ�������
			pstmt.setString(3, circle.getCircle_description());
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());

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

	public ResultSet findCirclesByUserID(int userID) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select * from " + "circle where user_id = ?"; // SQL���
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
}
