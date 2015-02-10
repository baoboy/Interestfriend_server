package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Utils.Constants;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;

public class CircleDaoImpl implements CircleDao {

	public int insertCircleToDB(Circle circle) {
		int autoIncKeyFromApi = -1;

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into circle(creator_id,circle_name,circle_description,circle_avatar,group_id,category,longitude,latitude,circle_create_time,last_request_time) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle.getCreator_id());
			pstmt.setString(2, circle.getCircle_name().replace("'", ""));// ���õڶ�������
			pstmt.setString(3, circle.getCircle_description().replace("'", ""));
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());
			pstmt.setInt(6, circle.getCategory());
			pstmt.setDouble(7, circle.getLongitude());
			pstmt.setDouble(8, circle.getLatitude());
			pstmt.setString(9, circle.getCircle_create_time());
			pstmt.setString(10, circle.getCircle_create_time());
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
	public ResultSet findCirclesByCategory(int category, int page) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		int startIndex = (page - 1) * 20;
		int endIndex = page * 20;
		Utils.print("page:" + startIndex + "   " + endIndex);

		String findByIDSQL = "select circle.* ,user.user_name from circle inner join user   on circle.creator_id=user.user_id and  category = ? order by circle.last_request_time desc  limit "
				+ startIndex + "," + endIndex; // SQL���
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
	public String getGroupIdByCircleID(int circle_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select group_id from circle where circle_id = ? ";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, circle_id);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return rs.getString("group_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return "";
	}

	@Override
	public ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude, int page) {
		double[] getAround = Utils.getAround(latitude, longitude,
				Constants.NEAR_RAIDUS);
		double minLat = getAround[0];
		double minLong = getAround[1];
		double maxLat = getAround[2];
		double maxLong = getAround[3];

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		int startIndex = (page - 1) * 20;
		int endIndex = page * 20;
		String findByIDSQL = "select circle.*,user.user_name from circle ,user where latitude BETWEEN ? AND ? and  longitude  BETWEEN ? AND ?  and circle.creator_id=`user`.user_id order by circle.last_request_time   limit "
				+ startIndex + "," + endIndex;
		Utils.print("page:" + startIndex + "   " + endIndex);
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setDouble(1, minLat);
			pstmt.setDouble(2, maxLat);
			pstmt.setDouble(3, minLong);
			pstmt.setDouble(4, maxLong);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
		} catch (Exception e) {
		} finally {
		}
		return rs;
	}

	@Override
	public boolean updateCircleDiscreption(Circle circle) {
		String sql = "UPDATE circle SET circle_description = ?  WHERE circle_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, circle.getCircle_description());
			pstmt.setInt(2, circle.getCircle_id());
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
	public boolean updateCircleLastRequestTime(int creator_id) {
		String sql = "UPDATE circle SET last_request_time = ?  WHERE creator_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, DateUtils.getRegisterTime());
			pstmt.setInt(2, creator_id);
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
	public boolean updateCircleLongitudeAndLatitude(double longitude,
			double latitude, int creator_id) {
		String sql = "UPDATE circle SET longitude = ?,latitude= ? WHERE creator_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setDouble(1, longitude);
			pstmt.setDouble(2, latitude);
			pstmt.setInt(3, creator_id);
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
	public boolean updateCircleLogo(int circle_id, String circle_logo) {
		String sql = "UPDATE circle SET circle_avatar = ?  WHERE circle_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, circle_logo);
			pstmt.setInt(2, circle_id);
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
}
