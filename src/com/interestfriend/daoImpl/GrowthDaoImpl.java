package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.bean.Growth;
import com.interestfriend.db.DBConnection;

public class GrowthDaoImpl implements GrowthDao {

	@Override
	public int insertGrowthToDB(Growth growth) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into growth(cid,publisher_id,content,time) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth.getCid());
			pstmt.setInt(2, growth.getPublisher_id());
			pstmt.setString(3, growth.getContent());
			pstmt.setString(4, growth.getTime());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); // ��ȡ����������
			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			} else {
			}
			return autoIncKeyFromApi;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return autoIncKeyFromApi;
	}

	@Override
	public ResultSet getGrowthByCid(int cid, int refushState, String refushTime) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if (refushState == 1) {
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and time >?  order by time desc limit 0,20";
		} else {
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and time <?  order by time desc limit 0,20";

		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setString(2, refushTime);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getGorwthPraiseCount(int growth_id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		ResultSet rs = null;

		String findByIDSQL = "select praise_count from growth where growth_id = ?"; // SQL���
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, growth_id); // ���ò���
			rs = pstmt.executeQuery(); // ִ�в�ѯ
			while (rs.next()) {
				return rs.getInt("praise_count");
			}
		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // �رս��������
			DBConnection.close(pstmt);
		}
		return 0;
	}

	@Override
	public boolean updateGrowthPraiseCount(int growth_id, int praise_count) {
		String sql = "UPDATE growth SET praise_count = ?  WHERE growth_id =?";
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, praise_count);
			pstmt.setInt(2, growth_id);
			int res = pstmt.executeUpdate(); // ִ�в�ѯ
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
		}
		return false;
	}

	@Override
	public int getGorwthCommentCount(int growth_id) {
		return 0;
	}

	@Override
	public boolean updateGrowthCommentCount(int growth_id, int praise_count) {
		return false;
	}
}
