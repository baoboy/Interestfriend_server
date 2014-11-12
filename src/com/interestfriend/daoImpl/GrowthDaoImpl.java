package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.bean.Growth;
import com.interestfriend.db.DBConnection;

public class GrowthDaoImpl implements GrowthDao {

	@Override
	public int insertGrowthToDB(Growth growth) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into growth(cid,publisher_id,content,time) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth.getCid());
			pstmt.setInt(2, growth.getPublisher_id());
			pstmt.setString(3, growth.getContent());
			pstmt.setString(4, growth.getTime());
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
	public ResultSet getGrowthByCid(int cid, int refushState, String refushTime) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if (refushState == 1) {
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and time >?  order by time desc limit 0,20";
		} else {
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and time <?  order by time desc limit 0,20";

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

	@Override
	public int getGorwthPraiseCount(int growth_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		String findByIDSQL = "select praise_count from growth where growth_id = ?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, growth_id); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return rs.getInt("praise_count");
			}
		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt);
		}
		return 0;
	}

	@Override
	public boolean updateGrowthPraiseCount(int growth_id, int praise_count) {
		String sql = "UPDATE growth SET praise_count = ?  WHERE growth_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setInt(1, praise_count);
			pstmt.setInt(2, growth_id);
			int res = pstmt.executeUpdate(); // 执行查询
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public int getGorwthCommentCount(int growth_id) {
		return 0;
	}

	@Override
	public boolean updateGrowthCommentCount(int growth_id, int praise_count) {
		return false;
	}
}
