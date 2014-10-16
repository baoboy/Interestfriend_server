package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.VideoDao;
import com.interestfriend.bean.Video;
import com.interestfriend.db.DBConnection;

public class VideoDaoImpl implements VideoDao {
	@Override
	public int insertVidoeToDB(Video video) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into video(cid,publisher_id,video_img,video_path,video_size,video_duration,time) values(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, video.getCid());
			pstmt.setInt(2, video.getPublisher_id());
			pstmt.setString(3, video.getVideo_img());
			pstmt.setString(4, video.getVideo_path());
			pstmt.setInt(5, video.getVideo_size());
			pstmt.setInt(6, video.getVideo_duration());
			pstmt.setString(7, video.getTime());
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
	public ResultSet getVideosByCid(int cid, int refushState, String refushTime) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if (refushState == 1) {
			sql = "select * from video where cid=? and time >?  order by time desc limit 0,20";
		} else {
			sql = "select * from video where cid=? and time <?  order by time desc limit 0,20";
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
