package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Idao.VideoCommentDao;
import com.interestfriend.bean.VideoComment;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.UserDaoFactory;

public class VideoCommentImpl implements VideoCommentDao {

	@Override
	public int insertComment(VideoComment comment) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "insert into video_comment (video_id,publisher_id,comment_content,comment_time,reply_someone_name,reply_someone_id)values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		int autoIncKeyFromApi = -1;
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, comment.getVideo_id());
			pstmt.setInt(2, comment.getPublisher_id());
			pstmt.setString(3, comment.getComment_content());// 设置第二个参数
			pstmt.setString(4, comment.getComment_time());
			pstmt.setString(5, comment.getReply_someone_name());
			pstmt.setInt(6, comment.getReply_someone_id());
			pstmt.executeUpdate(); // 执行更新
			rs = pstmt.getGeneratedKeys(); // 获取自增主键！
			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			} else {
			}
			return autoIncKeyFromApi;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			// DBConnection.close(conn); // 关闭连接对象
		}
		return autoIncKeyFromApi;
	}

	@Override
	public List<VideoComment> getCommentByVideoID(int video_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from video_comment where video_id=?";
		List<VideoComment> comments = new ArrayList<VideoComment>();
		UserDao userDao = UserDaoFactory.getUserDaoInstance();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, video_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				VideoComment comment = new VideoComment();
				comment.setComment_content(res.getString("comment_content"));
				comment.setComment_id(res.getInt("comment_id"));
				comment.setComment_time(res.getString("comment_time"));
				comment.setReply_someone_id(res.getInt("reply_someone_id"));
				comment.setReply_someone_name(res
						.getString("reply_someone_name"));
				int publisher_id = res.getInt("publisher_id");
				comment.setPublisher_id(publisher_id);
				comment.setVideo_id(res.getInt("video_id"));
				String[] nameAndAvatar = userDao
						.getUserNameAndAvatar(publisher_id);
				comment.setPublisher_avatar(nameAndAvatar[1]);
				comment.setPublisher_name(nameAndAvatar[0]);
				comments.add(comment);
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
