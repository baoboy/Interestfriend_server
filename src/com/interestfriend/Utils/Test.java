package com.interestfriend.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.CircleDaoFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
		// for (int i = 1; i < 80; i++) {
		// Circle circle = new Circle();
		// circle.setCreator_id(-1);
		// circle.setCategory(1);
		// circle.setLatitude(39.9160140);
		// circle.setLongitude(116.4659610);
		// circle.setCircle_name("第" + i + "个圈子");
		// dao.insertCircleToDB(circle);
		// }
		// System.out.println("finish");
		findCirclesByLongitudeAndLatitude(0, 0, 0);
	}

	public static ResultSet findCirclesByLongitudeAndLatitude(double longitude,
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
		String sql = "create function  fun_a(in_int int) RETURNS int BEGIN  declare i_rand int; declare i_return int; set i_rand=5;set i_return =1;   return i_return; END;";
		try {
			conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// String findByIDSQL = "select circle.*,user.user_name,"
		// +
		// " from circle ,user where latitude BETWEEN ? AND ? and  longitude  BETWEEN ? AND ?  and circle.creator_id=`user`.user_id order by circle.last_request_time   limit "
		// + startIndex + "," + endIndex;
		sql = "select dbo.fun_a(circle_id)bb from circle";
		Utils.print("page:" + startIndex + "   " + endIndex);
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			// pstmt.setDouble(1, minLat);
			// pstmt.setDouble(2, maxLat);
			// pstmt.setDouble(3, minLong);
			// pstmt.setDouble(4, maxLong);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				System.out.println(rs.getInt("bb"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
		}
		return rs;
	}
}
