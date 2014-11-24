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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into sms_code(user_cellphone,sms_code,time) values(?,?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, code.getUser_cellphone());// ���õڶ�������
			pstmt.setString(2, code.getSms_code());
			pstmt.setString(3, code.getTime());

			int res = pstmt.executeUpdate(); // ִ�и���
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public String findCodeByCellphone(String user_cellphone) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "select sms_code from sms_code where user_cellphone = ? ";
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, user_cellphone);
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return rs.getString("sms_code");
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
	public boolean delCodeByUserCellPhone(String user_cellphone) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "delete from sms_code where user_cellphone=?";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setString(1, user_cellphone);// ���õڶ�������
			int res = pstmt.executeUpdate(); // ִ�и���
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

}
