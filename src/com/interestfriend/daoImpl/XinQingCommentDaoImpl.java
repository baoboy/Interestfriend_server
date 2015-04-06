package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.UserDao;
import com.interestfriend.Idao.XinQingCommentDao;
import com.interestfriend.bean.XinQingComment;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.UserDaoFactory;

public class XinQingCommentDaoImpl implements XinQingCommentDao {

	@Override
	public int insertComment(XinQingComment comment) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "insert into xinqing_comment (xinqing_id,publisher_id,comment_content,comment_time,reply_someone_name,reply_someone_id)values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		int autoIncKeyFromApi = -1;

		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, comment.getXinqing_id());
			pstmt.setInt(2, comment.getPublisher_id());
			pstmt.setString(3, comment.getComment_content().replace("'", ""));// 设置第二个参数
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
	public List<XinQingComment> getCommentByXinQingID(int xinqing_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from xinqing_comment where xinqing_id=? order by comment_time desc";
		List<XinQingComment> comments = new ArrayList<XinQingComment>();
		UserDao userDao = UserDaoFactory.getUserDaoInstance();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				XinQingComment comment = new XinQingComment();
				comment.setComment_content(res.getString("comment_content"));
				comment.setComment_id(res.getInt("comment_id"));
				comment.setComment_time(res.getString("comment_time"));
				comment.setReply_someone_id(res.getInt("reply_someone_id"));
				comment.setReply_someone_name(res
						.getString("reply_someone_name"));
				int publisher_id = res.getInt("publisher_id");
				comment.setPublisher_id(publisher_id);
				comment.setXinqing_id(res.getInt("xinqing_id"));
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

	@Override
	public boolean deleteCommentByID(int comment_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String sql = "delete from xinqing_comment where comment_id=?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, comment_id);
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
