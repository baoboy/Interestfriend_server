package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.bean.Growth;
import com.interestfriend.db.DBConnection;

public class GrowthDaoImpl implements GrowthDao {

	@Override
	public boolean insertGrowthToDB(Growth growth) {
		Connection conn = DBConnection.getConnection();
		String sql = "insert into growth(cid,publisher_id,content,time) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth.getCid());
			pstmt.setInt(2, growth.getPublisher_id());
			pstmt.setString(3, growth.getContent());
			pstmt.setString(4, growth.getTime());
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
