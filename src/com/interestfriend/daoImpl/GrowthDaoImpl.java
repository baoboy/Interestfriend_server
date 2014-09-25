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
			rs = pstmt.getGeneratedKeys(); // 获取自增主键！
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
	public ResultSet getGrowthByCid(int cid) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from growth where cid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
