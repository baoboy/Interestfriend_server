package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;

public class CircleDaoImpl implements CircleDao {

	public int insertCircleToDB(Circle circle) {
		int autoIncKeyFromApi = -1;

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into circle(user_id,circle_name,circle_description,circle_avatar,group_id,category,longitude,latitude) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle.getUser_id());
			pstmt.setString(2, circle.getCircle_name());// ���õڶ�������
			pstmt.setString(3, circle.getCircle_description());
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());
			pstmt.setInt(6, circle.getCategory());
			pstmt.setDouble(7, circle.getLongitude());
			pstmt.setDouble(8, circle.getLatitude());
			pstmt.executeUpdate(); // ִ�и���
			rs = pstmt.getGeneratedKeys(); // ��ȡ����������
			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			} else {
			}
			return autoIncKeyFromApi;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
			// DBConnection.close(conn); // �ر����Ӷ���
		}
		return autoIncKeyFromApi;
	}

	public ResultSet findCirclesByUserID(int userID) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		String findByIDSQL = "select * from circle where user_id = ?"; // SQL���
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
	public ResultSet findCirclesByCategory(int category) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select * from circle where category = ?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, category); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			// DBConnection.close(rs); // �رս��������
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	@Override
	public String getGroupIdByCircleID() {
		return null;
	}

	@Override
	public ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude) {
		double[] getAround = Utils.getAround(latitude, longitude, 4000);
		double minLat = getAround[0];
		double minLong = getAround[1];
		double maxLat = getAround[2];
		double maxLong = getAround[3];

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		String findByIDSQL = "select * from circle where latitude BETWEEN ? AND ? and  longitude  BETWEEN ? AND ?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setDouble(1, minLat);
			pstmt.setDouble(2, maxLat);
			pstmt.setDouble(3, minLong);
			pstmt.setDouble(4, maxLong);

			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
		} finally {
			// DBConnection.close(rs); // �رս��������
			// DBConnection.close(pstmt);
		}
		return rs;
	}
}
