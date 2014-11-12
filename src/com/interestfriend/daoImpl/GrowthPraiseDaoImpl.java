package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.db.DBConnection;

public class GrowthPraiseDaoImpl implements GrowthPraiseDao {

	@Override
	public boolean insertPraiseToDB(GrowthPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into growth_praise(user_id,growth_id) values(?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getUser_id());
			pstmt.setInt(2, praise.getGrowth_id());// ���õڶ�������
			int count = pstmt.executeUpdate(); // ִ�и���
			System.out.println("praise:" + count);
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public int findPraiseByUserID(GrowthPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select growth_id from growth_praise where user_id = ? and growth_id =?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getUser_id()); // ���ò���
			pstmt.setInt(2, praise.getGrowth_id()); // ���ò���

			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt);
		}
		return 0;
	}

	@Override
	public boolean cancelPraise(GrowthPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		String findByIDSQL = "DELETE FROM growth_praise WHERE growth_id=? and user_id=?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getGrowth_id()); // ���ò���
			pstmt.setInt(2, praise.getUser_id());
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			System.out.println("cancel:" + res);
			return res > 0;
		} catch (Exception e) {
		} finally {
			DBConnection.close(pstmt);
		}
		return false;
	}

}
