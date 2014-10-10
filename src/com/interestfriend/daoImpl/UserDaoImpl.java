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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into USER(user_name,user_cellphone,user_password,user_gender,user_avatar,user_birthday) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, user.getUserName());// ���õڶ�������
			pstmt.setString(2, user.getUserCellPhone());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserAvatar());
			pstmt.setString(6, user.getUserBirthday());

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
}
