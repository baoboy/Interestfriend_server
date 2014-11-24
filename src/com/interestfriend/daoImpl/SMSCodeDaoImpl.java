package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.SMSCodeDao;
import com.interestfriend.bean.SMSCode;
import com.interestfriend.db.DBConnection;

public class SMSCodeDaoImpl implements SMSCodeDao {

	@Override
	public boolean insertToDB(SMSCode code) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into sms_code(user_cellphone,sms_code,time) values(?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setString(1, code.getUser_cellphone());// 设置第二个参数
			pstmt.setString(2, code.getSms_code());
			pstmt.setString(3, code.getTime());

			int res = pstmt.executeUpdate(); // 执行更新
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public String findCodeByCellphone(String user_cellphone) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "select sms_code from sms_code where user_cellphone = ? ";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, user_cellphone);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return rs.getString("sms_code");
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
	public boolean delCodeByUserCellPhone(String user_cellphone) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "delete from sms_code where user_cellphone=?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setString(1, user_cellphone);// 设置第二个参数
			int res = pstmt.executeUpdate(); // 执行更新
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

}
