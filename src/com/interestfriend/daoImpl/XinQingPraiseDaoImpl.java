package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.XinQingPraiseDao;
import com.interestfriend.bean.XinQingPraise;
import com.interestfriend.db.DBConnection;

public class XinQingPraiseDaoImpl implements XinQingPraiseDao {

	@Override
	public boolean insertPraiseToDB(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into xinqing_praise(user_id,xinqing_id) values(?,?)";
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getUser_id());
			pstmt.setInt(2, praise.getXinqing_id());// ���õڶ�������
			int count = pstmt.executeUpdate(); // ִ�и���
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public int findPraiseByUserID(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;
		String findByIDSQL = "select xinqing_id from xinqing_praise where user_id = ? and xinqing_id =?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getUser_id()); // ���ò���
			pstmt.setInt(2, praise.getXinqing_id()); // ���ò���

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
	public boolean cancelPraise(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		String findByIDSQL = "DELETE FROM xinqing_praise WHERE xinqing_id=? and user_id=?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise.getXinqing_id()); // ���ò���
			pstmt.setInt(2, praise.getUser_id());
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			return res > 0;
		} catch (Exception e) {
		} finally {
			DBConnection.close(pstmt);
		}
		return false;
	}

	@Override
	public List<XinQingPraise> findPraiseUserByXinQingID(int xinqing_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select user.user_avatar,xinqing_praise.user_id from user, xinqing_praise where xinqing_id=? and user.user_id=xinqing_praise.user_id";
		List<XinQingPraise> praises = new ArrayList<XinQingPraise>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				XinQingPraise praise = new XinQingPraise();
				praise.setUser_avatar(res.getString("user_avatar"));
				praise.setUser_id(res.getInt("user_id"));
				praises.add(praise);
			}
			return praises;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return null;
	}

	@Override
	public int getXinQingPraiseCount(int xinqing_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		String findByIDSQL = "select user_id from xinqing_praise where xinqing_id = ?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, xinqing_id); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			rs.last();
			return rs.getRow(); // ���ResultSet��������

		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt);
		}
		return 0;
	}
}
