package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.Idao.UserDao;
import com.interestfriend.bean.Comment;
import com.interestfriend.bean.GrowthImage;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.UserDaoFactory;

public class CommentDaoImpl implements CommentDao {

	@Override
	public boolean insertComment(Comment comment) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String sql = "insert into comment (growth_id,publisher_id,comment_content,comment_time)values(?,?,?,?)";

		PreparedStatement pstmt = null; // ����Ԥ�������

		try {
			pstmt = conn.prepareStatement(sql); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, comment.getGrowth_id());
			pstmt.setInt(2, comment.getPublisher_id());
			pstmt.setString(3, comment.getComment_content());// ���õڶ�������
			pstmt.setString(4, comment.getComment_time());
			return pstmt.executeUpdate() > 0; // ִ�и���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
			// DBConnection.close(conn); // �ر����Ӷ���
		}
		return false;
	}

	@Override
	public List<Comment> getCommentByGrowthID(int growth_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from comment where growth_id=?";
		List<Comment> comments = new ArrayList<Comment>();
		UserDao userDao = UserDaoFactory.getUserDaoInstance();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				Comment comment = new Comment();
				comment.setComment_content(res.getString("comment_content"));
				comment.setComment_id(res.getInt("comment_id"));
				comment.setComment_time(res.getString("comment_time"));
				int publisher_id = res.getInt("publisher_id");
				comment.setPublisher_id(publisher_id);
				comment.setGrowth_id(res.getInt("growth_id"));
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
