package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.Idao.UserDao;
import com.interestfriend.bean.Comment;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.UserDaoFactory;

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

	@Override
	public List<GrowthPraise> findPraiseUserByGrowthID(int growth_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select user.user_avatar,growth_praise.user_id from user, growth_praise where growth_id=? and user.user_id=growth_praise.user_id";
		List<GrowthPraise> comments = new ArrayList<GrowthPraise>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				GrowthPraise praise = new GrowthPraise();
				praise.setUser_avatar(res.getString("user_avatar"));
				praise.setUser_id(res.getInt("user_id"));
				comments.add(praise);
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return null;
	}
}
