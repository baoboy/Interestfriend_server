package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.XinQingPraiseDao;
import com.interestfriend.bean.XinQingPraise;
import com.interestfriend.db.DBConnection;

public class XinQingPraiseDaoImpl implements XinQingPraiseDao {

	@Override
	public boolean insertPraiseToDB(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into xinqing_praise(user_id,xinqing_id) values(?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, praise.getUser_id());
			pstmt.setInt(2, praise.getXinqing_id());// 设置第二个参数
			int count = pstmt.executeUpdate(); // 执行更新
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
		}
		return false;
	}

	@Override
	public int findPraiseByUserID(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		String findByIDSQL = "select xinqing_id from xinqing_praise where user_id = ? and xinqing_id =?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, praise.getUser_id()); // 设置参数
			pstmt.setInt(2, praise.getXinqing_id()); // 设置参数

			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt);
		}
		return 0;
	}

	@Override
	public boolean cancelPraise(XinQingPraise praise) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		String findByIDSQL = "DELETE FROM xinqing_praise WHERE xinqing_id=? and user_id=?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, praise.getXinqing_id()); // 设置参数
			pstmt.setInt(2, praise.getUser_id());
			int res = pstmt.executeUpdate(); // 执行查询
			return res > 0;
		} catch (Exception e) {
		} finally {
			DBConnection.close(pstmt);
		}
		return false;
	}

	@Override
	public List<XinQingPraise> findPraiseUserByXinQingID(int xinqing_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select user.user_avatar,xinqing_praise.user_id from user, xinqing_praise where xinqing_id=? and user.user_id=xinqing_praise.user_id";
		List<XinQingPraise> praises = new ArrayList<XinQingPraise>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing_id);
			res = pstmt.executeQuery();
			while (res.next()) {
				XinQingPraise praise = new XinQingPraise();
				praise.setUser_avatar(res.getString("user_avatar"));
				praise.setUser_id(res.getInt("user_id"));
				praises.add(praise);
			}
			return praises;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);
			DBConnection.close(res);
		}
		return null;
	}

	@Override
	public int getXinQingPraiseCount(int xinqing_id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;

		String findByIDSQL = "select user_id from xinqing_praise where xinqing_id = ?"; // SQL语句
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, xinqing_id); // 设置参数
			rs = pstmt.executeQuery(); // 执行查询
			rs.last();
			return rs.getRow(); // 获得ResultSet的总行数

		} catch (Exception e) {
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt);
		}
		return 0;
	}
}
