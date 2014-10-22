package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.bean.Comment;
import com.interestfriend.db.DBConnection;

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
}
