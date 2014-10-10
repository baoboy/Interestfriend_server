package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.bean.User;
import com.interestfriend.db.DBConnection;

public class UserDaoImpl implements UserDao {

	public boolean insertUserToDB(User user) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into USER(user_name,user_cellphone,user_password,user_gender,user_avatar,user_birthday) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setString(1, user.getUserName());// 设置第二个参数
			pstmt.setString(2, user.getUserCellPhone());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserAvatar());
			pstmt.setString(6, user.getUserBirthday());

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

	public boolean verifyCellphone(String cellPhone) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "select user_id from user where user_cellphone = ? ";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, cellPhone);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public int userLogon(String telPhone, String password) {
		boolean isExist = verifyCellphone(telPhone);
		if (!isExist) {
			return -1;// 手机号不存在
		}
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "select user_id from user where user_cellphone = ? and user_password = ?";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, telPhone);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return rs.getInt("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return -2;// 密码错误
	}
}
