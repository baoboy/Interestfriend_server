package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.interestfriend.Idao.CommentDao;
import com.interestfriend.Idao.GrowthDao;
import com.interestfriend.Idao.GrowthImageDao;
import com.interestfriend.Idao.GrowthPraiseDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.bean.Growth;
import com.interestfriend.bean.GrowthPraise;
import com.interestfriend.db.DBConnection;
import com.interestfriend.enums.Status;
import com.interestfriend.factory.CommentDaoFactory;
import com.interestfriend.factory.GrowthImageDaoFactory;
import com.interestfriend.factory.GrowthPraiseDaoFactory;

public class GrowthDaoImpl implements GrowthDao {

	@Override
	public int insertGrowthToDB(Growth growth) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into growth(cid,publisher_id,content,time,last_update_time,state) values(?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth.getCid());
			pstmt.setInt(2, growth.getPublisher_id());
			pstmt.setString(3, growth.getContent());
			pstmt.setString(4, growth.getTime());
			pstmt.setString(5, growth.getTime());
			pstmt.setString(6, Status.ADD.name());
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
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and  (time>? or last_update_time > ?) order by time desc limit 0,20";
		} else {
			sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where cid=? and (time <? or last_update_time > ?) order by time desc limit 0,20";

		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setString(2, refushTime);
			pstmt.setString(3, refushTime);
			System.out.println("resushTIme:" + refushTime);
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

	@Override
	public String getUserHuanXinNameByGrowthID(int growth_id,
			int growth_publisher_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String findByIDSQL = "SELECT `user`.user_chat_id FROM `user` ,growth WHERE growth.growth_id=? and `user`.user_id=?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, growth_id); // 设置参数
			pstmt.setInt(2, growth_publisher_id);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return rs.getString("user_chat_id");
			}
		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt);
		}
		return "";
	}

	@Override
	public Growth getGrowthByGrowthIDGrowth(int cid, int growth_id, int user_id) {
		Connection conn = DBConnection.getConnection();
		Growth growth = new Growth();
		ResultSet res = null;
		PreparedStatement pstmt = null;
		String sql = "";
		sql = "select growth.*,`user`.user_avatar,`user`.user_name  from  growth  INNER JOIN  `user`  on  growth.publisher_id =`user`.user_id where growth_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, growth_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				GrowthImageDao imgDao = GrowthImageDaoFactory
						.getGrowthImageDaoInstance();
				CommentDao coDao = CommentDaoFactory.getInstances();
				GrowthPraiseDao gDao = GrowthPraiseDaoFactory.getInstance();
				GrowthPraise praise = new GrowthPraise();
				praise.setUser_id(user_id);
				praise.setGrowth_id(growth_id);
				growth.setCid(cid);
				growth.setIsPraise(gDao.findPraiseByUserID(praise));
				growth.setGrowth_id(growth_id);
				growth.setContent(res.getString("content"));
				growth.setTime(res.getString("time"));
				int publisher_id = res.getInt("publisher_id");
				growth.setPublisher_id(publisher_id);
				growth.setImages(imgDao.getImagesByGrowthID(cid, growth_id));
				growth.setComments(coDao.getCommentByGrowthID(growth_id));
				growth.setPublisher_avatar(res.getString("user_avatar"));
				growth.setPublisher_name(res.getString("user_name"));
				growth.setPraise_count(res.getInt("praise_count"));
				growth.setPraises(gDao.findPraiseUserByGrowthID(growth_id));

				return growth;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return growth;
	}

	@Override
	public boolean updateGrowthUpdateTime(int growth_id) {
		String sql = "UPDATE growth SET last_update_time = ? ,state=? WHERE growth_id =?";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(sql); // 获得预处理对象并赋值
			pstmt.setString(1, DateUtils.getGrowthShowTime());
			pstmt.setString(2, Status.UPDATE.name());
			pstmt.setInt(3, growth_id);
			int res = pstmt.executeUpdate(); // 执行查询
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}
}
