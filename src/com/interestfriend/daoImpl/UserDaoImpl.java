package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.PinYinUtil;
import com.interestfriend.Utils.Utils;
import com.interestfriend.bean.User;
import com.interestfriend.db.DBConnection;

public class UserDaoImpl implements UserDao {

	public boolean insertUserToDB(User user) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into USER(user_name,user_cellphone,user_password,user_gender,user_avatar,user_birthday,user_register_time,user_sort_key,user_pinyin_str,user_chat_id) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, user.getUserName());// ���õڶ�������
			pstmt.setString(2, user.getUserCellPhone());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserAvatar());
			pstmt.setString(6, user.getUserBirthday());
			pstmt.setString(7, user.getUserRegisterTime());
			pstmt.setString(8, user.getSortKey());
			pstmt.setString(9, user.getPinYinFir());
			pstmt.setString(10, user.getUserChatId());

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

	public boolean verifyCellphone(String cellPhone) {
		Utils.print("cellPhone:" + cellPhone);
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select user_id from user where user_cellphone = ? ";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, cellPhone);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public int userLogon(String telPhone, String password) {
		System.out.println(telPhone + "   " + password);
		boolean isExist = verifyCellphone(telPhone);
		if (!isExist) {
			return -1;// �ֻ��Ų�����
		}
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select user_id from user where user_cellphone = ? and user_password = ?";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, telPhone);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return -2;// �������
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
		if ("��������".equals(column)) {
			sql = "UPDATE user SET user_declaration = '" + value
					+ "' WHERE user_id =?";
		} else if ("���˽���".equals(column)) {
			sql = "UPDATE user SET user_description = '" + value
					+ "' WHERE user_id =?";
		}
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, user_id);
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
	public boolean updateUserAvatar(User user) {
		// String sql =
		// "UPDATE user SET user_avatar = ? , user_last_update_time =?,user_state=? WHERE user_id =?";
		String sql = "UPDATE user SET user_avatar = ?  WHERE user_id =?";

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, user.getUserAvatar());
			// pstmt.setLong(2, user.getUserLastUpdateTime());
			// pstmt.setString(3, user.getUserState());
			pstmt.setInt(2, user.getUserID());
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
	public String[] getUserNameAndAvatar(int user_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select user_name,user_avatar from user where user_id = ?";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String resStr[] = new String[2];

		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				resStr[0] = rs.getString("user_name");
				resStr[1] = rs.getString("user_avatar");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return resStr;
	}

	@Override
	public boolean updataLoginPassword(String cell_phone, String password) {
		String sql = "UPDATE user SET user_password = ?  WHERE user_cellphone =?";

		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, password);
			pstmt.setString(2, cell_phone);
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
	public String findUserChatIDByUserID(int user_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select user_chat_id from user where user_id = ? ";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return rs.getString("user_chat_id");
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
	public boolean updateUserName(int user_id, String name,
			String user_sort_key, String user_pinyin) {
		String sql = "UPDATE user SET user_name = '" + name
				+ "' ,user_sort_key= '" + user_sort_key
				+ " ', user_pinyin_str= '" + user_pinyin + "' WHERE user_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, user_id);
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
