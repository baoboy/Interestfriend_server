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

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into circle(creator_id,circle_name,circle_description,circle_avatar,group_id,category,longitude,latitude,circle_create_time,last_request_time) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circle.getCreator_id());
			pstmt.setString(2, circle.getCircle_name().replace("'", ""));// 设置第二个参数
			pstmt.setString(3, circle.getCircle_description().replace("'", ""));
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());
			pstmt.setInt(6, circle.getCategory());
			pstmt.setDouble(7, circle.getLongitude());
			pstmt.setDouble(8, circle.getLatitude());
			pstmt.setString(9, circle.getCircle_create_time());
			pstmt.setString(10, circle.getCircle_create_time());
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
	public ResultSet findCirclesByCategory(int category, int page) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		int startIndex = (page - 1) * 20;
		int endIndex = page * 20;
		Utils.print("page:" + startIndex + "   " + endIndex);

		String findByIDSQL = "select circle.* ,user.user_name from circle inner join user   on circle.creator_id=user.user_id and  category = ? order by circle.last_request_time desc  limit "
				+ startIndex + "," + endIndex; // SQL语句
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
			double latitude, int page) {
		double[] getAround = Utils.getAround(latitude, longitude,
				Constants.NEAR_RAIDUS);
		double minLat = getAround[0];
		double minLong = getAround[1];
		double maxLat = getAround[2];
		double maxLong = getAround[3];

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		int startIndex = (page - 1) * 20;
		int endIndex = page * 20;
		String findByIDSQL = "select circle.*,user.user_name from circle ,user where latitude BETWEEN ? AND ? and  longitude  BETWEEN ? AND ?  and circle.creator_id=`user`.user_id order by circle.last_request_time   limit "
				+ startIndex + "," + endIndex;
		Utils.print("page:" + startIndex + "   " + endIndex);
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

	@Override
	public boolean updateCircleLastRequestTime(int creator_id) {
		String sql = "UPDATE circle SET last_request_time = ?  WHERE creator_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, DateUtils.getRegisterTime());
			pstmt.setInt(2, creator_id);
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
	public boolean updateCircleLongitudeAndLatitude(double longitude,
			double latitude, int creator_id) {
		String sql = "UPDATE circle SET longitude = ?,latitude= ? WHERE creator_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setDouble(1, longitude);
			pstmt.setDouble(2, latitude);
			pstmt.setInt(3, creator_id);
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
	public boolean updateCircleLogo(int circle_id, String circle_logo) {
		String sql = "UPDATE circle SET circle_avatar = ?  WHERE circle_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, circle_logo);
			pstmt.setInt(2, circle_id);
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
