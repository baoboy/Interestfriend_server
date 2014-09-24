package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.bean.GrowthImage;
import com.interestfriend.db.DBConnection;

public class GrowthImageDaoImpl implements GrowthImageDao {

	@Override
	public boolean insertGrowthImageToDB(List<GrowthImage> listImages) {
		Connection conn = DBConnection.getConnection();
		String sql = "insert into growth_img (cid,growth_id,image_url) values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (GrowthImage img : listImages) {
				pstmt.setInt(1, img.getCid());
				pstmt.setInt(2, img.getGrowth_id());
				pstmt.setString(3, img.getImg_url());
				pstmt.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
