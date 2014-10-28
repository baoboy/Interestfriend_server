package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.PinYinUtil;
import com.interestfriend.bean.User;
import com.interestfriend.db.DBConnection;

public class UserDaoImpl implements UserDao {

	public boolean insertUserToDB(User user) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into USER(user_name,user_cellphone,user_password,user_gender,user_avatar,user_birthday,user_register_time,user_last_update_time,user_sort_key,user_pinyin_str) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setString(1, user.getUserName());// 设置第二个参数
			pstmt.setString(2, user.getUserCellPhone());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserAvatar());
			pstmt.setString(6, user.getUserBirthday());
			pstmt.setString(7, user.getUserRegisterTime());
			pstmt.setLong(8, user.getUserLastUpdateTime());
			pstmt.setString(9, user.getSortKey());
			pstmt.setString(10, user.getPinYinFir());

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
		System.out.println(telPhone + "   " + password);
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

	@Override
	public ResultSet getUserInfo(int user_id) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from user where user_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUserInfo(int user_id, String column, String value) {
		String sql = "";
		// if ("昵称".equals(column)) {
		// sql = "UPDATE user SET user_name = '" + value
		// + "' , user_last_update_time ="
		// + DateUtils.getLastUpdateTime() + ",user_state= 'UPDATE' "
		// + " ,user_sort_key= '"
		// + PinYinUtil.converterToFirstSpell(value)
		// + " ', user_pinyin_str= '"
		// + PinYinUtil.converterToSpell(value) + "' WHERE user_id =?";
		// } else if ("交友宣言".equals(column)) {
		// sql = "UPDATE user SET user_declaration = '" + value
		// + "' , user_last_update_time ="
		// + DateUtils.getLastUpdateTime() + ",user_state= 'UPDATE' "
		// + " WHERE user_id =?";
		// } else if ("个人介绍".equals(column)) {
		// sql = "UPDATE user SET user_description = '" + value
		// + "' , user_last_update_time ="
		// + DateUtils.getLastUpdateTime() + ",user_state= 'UPDATE' "
		// + " WHERE user_id =?";
		// }

		if ("昵称".equals(column)) {
			sql = "UPDATE user SET user_name = '" + value
					+ "' ,user_sort_key= '"
					+ PinYinUtil.converterToFirstSpell(value)
					+ " ', user_pinyin_str= '"
					+ PinYinUtil.converterToSpell(value) + "' WHERE user_id =?";
		} else if ("交友宣言".equals(column)) {
			sql = "UPDATE user SET user_declaration = '" + value
					+ "' WHERE user_id =?";
		} else if ("个人介绍".equals(column)) {
			sql = "UPDATE user SET user_description = '" + value
					+ "' WHERE user_id =?";
		}
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, user_id);
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
	public boolean updateUserAvatar(User user) {
		// String sql =
		// "UPDATE user SET user_avatar = ? , user_last_update_time =?,user_state=? WHERE user_id =?";
		String sql = "UPDATE user SET user_avatar = ?  WHERE user_id =?";

		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, user.getUserAvatar());
			// pstmt.setLong(2, user.getUserLastUpdateTime());
			// pstmt.setString(3, user.getUserState());
			pstmt.setInt(2, user.getUserID());
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
	public String[] getUserNameAndAvatar(int user_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "select user_name,user_avatar from user where user_id = ?";
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String resStr[] = new String[2];

		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				resStr[0] = rs.getString("user_name");
				resStr[1] = rs.getString("user_avatar");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return resStr;
	}

}
