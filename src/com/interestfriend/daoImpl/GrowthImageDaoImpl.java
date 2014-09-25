package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	@Override
	public List<GrowthImage> getImagesByGrowthID(int cid, int growth_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from growth_img where cid=? and growth_id=?";
		List<GrowthImage> images = new ArrayList<GrowthImage>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, growth_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				GrowthImage img = new GrowthImage();
				img.setImg_url(res.getString("image_url"));
				img.setImg_id(res.getInt("image_id"));
				images.add(img);
			}
			return images;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return null;
	}
}
