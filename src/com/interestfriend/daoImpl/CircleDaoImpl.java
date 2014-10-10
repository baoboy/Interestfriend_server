package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.bean.Circle;
import com.interestfriend.db.DBConnection;

public class CircleDaoImpl implements CircleDao {

	public int insertCircleToDB(Circle circle) {
		int autoIncKeyFromApi = -1;

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into circle(user_id,circle_name,circle_description,circle_avatar,group_id) values(?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, circle.getUser_id());
			pstmt.setString(2, circle.getCircle_name());// 设置第二个参数
			pstmt.setString(3, circle.getCircle_description());
			pstmt.setString(4, circle.getCircle_avatar());
			pstmt.setString(5, circle.getGroup_id());

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
		String findByIDSQL = "select * from circle where category = ?"; // SQL语句
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
}
