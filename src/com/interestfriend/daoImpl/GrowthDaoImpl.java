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
	public ResultSet getGrowthByCid(int cid, int refushState, String refushTime) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if (refushState == 1) {
			// sql =
			// "select * from growth where cid=? and time >?  order by time desc limit 0,20";
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and time >?  order by time desc limit 0,20";
		} else {
			// sql =
			// "select * from growth where cid=? and time <?  order by time desc limit 0,20";
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
}
