package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.XinQingDao;
import com.interestfriend.bean.XinQing;
import com.interestfriend.db.DBConnection;

public class XinQingDaoImpl implements XinQingDao {

	@Override
	public int insertToDB(XinQing xinqing) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into xinqing(publisher_id,content,publish_time,image_url) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing.getPublisher_id());
			pstmt.setString(2, xinqing.getContent().replace("'", ""));
			pstmt.setString(3, xinqing.getPublish_time());
			pstmt.setString(4, xinqing.getImage_url());
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

}
