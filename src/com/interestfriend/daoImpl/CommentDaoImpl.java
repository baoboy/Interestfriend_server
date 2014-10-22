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
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "insert into comment (growth_id,publisher_id,comment_content,comment_time)values(?,?,?,?)";

		PreparedStatement pstmt = null; // 声明预处理对象

		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, comment.getGrowth_id());
			pstmt.setInt(2, comment.getPublisher_id());
			pstmt.setString(3, comment.getComment_content());// 设置第二个参数
			pstmt.setString(4, comment.getComment_time());
			return pstmt.executeUpdate() > 0; // 执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			// DBConnection.close(conn); // 关闭连接对象
		}
		return false;
	}
}
