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

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into circle(creator_id,circle_name,circle_description,circle_avatar,group_id,category,longitude,latitude,circle_create_time) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circle.getCreator_id());
			pstmt.setString(2, circle.getCircle_name());// 设置第二个参数
			pstmt.setString(3, circle.getCircle_description());
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());
			pstmt.setInt(6, circle.getCategory());
			pstmt.setDouble(7, circle.getLongitude());
			pstmt.setDouble(8, circle.getLatitude());
			pstmt.setString(9, circle.getCircle_create_time());
			pstmt.executeUpdate(); // 执行更新
			rs = pstmt.getGeneratedKeys(); // 获取自增主键！
			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			} else {
			}
			return autoIncKeyFromApi;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			// DBConnection.close(conn); // 关闭连接对象
		}
		return autoIncKeyFromApi;
	}

	public ResultSet findCirclesByUserID(int userID) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		String findByIDSQL = "select * from circle where user_id = ?"; // SQL语句
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
	public ResultSet findCirclesByCategory(int category) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String findByIDSQL = "select circle.* ,user.user_name from circle inner join user   on circle.creator_id=user.user_id and  category = ?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, category); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			// DBConnection.close(rs); // 关闭结果集对象
			// DBConnection.close(pstmt);
		}
		return rs;
	}

	@Override
	public String getGroupIdByCircleID(int circle_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "select group_id from circle where circle_id = ? ";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, circle_id);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return rs.getString("group_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return "";
	}

	@Override
	public ResultSet findCirclesByLongitudeAndLatitude(double longitude,
			double latitude) {
		double[] getAround = Utils.getAround(latitude, longitude, 4000);
		double minLat = getAround[0];
		double minLong = getAround[1];
		double maxLat = getAround[2];
		double maxLong = getAround[3];

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		String findByIDSQL = "select * from circle where latitude BETWEEN ? AND ? and  longitude  BETWEEN ? AND ?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setDouble(1, minLat);
			pstmt.setDouble(2, maxLat);
			pstmt.setDouble(3, minLong);
			pstmt.setDouble(4, maxLong);

			rs = pstmt.executeQuery(); // 执行查询
		} catch (Exception e) {
		} finally {
		}
		return rs;
	}

	@Override
	public boolean updateCircleDiscreption(Circle circle) {
		String sql = "UPDATE circle SET circle_description = ?  WHERE circle_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, circle.getCircle_description());
			pstmt.setInt(2, circle.getCircle_id());
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
}
