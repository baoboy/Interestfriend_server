package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.interestfriend.Idao.RequestJoinCircleDao;
import com.interestfriend.bean.RequestJoinCircle;
import com.interestfriend.db.DBConnection;

public class RequestJoinCircleDaoImpl implements RequestJoinCircleDao {

	@Override
	public boolean requestJoinCircle(RequestJoinCircle joinCircle) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into qequest_join_circle(join_circle_id,join_circle_creator_id,request_join_circle_user_id) values(?,?,?)";

		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, joinCircle.getJoin_circle_id());
			pstmt.setInt(2, joinCircle.getJoin_circle_creator_id());// 设置第二个参数
			pstmt.setInt(3, joinCircle.getRequest_join_circle_user_id());
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
	public boolean receiveRequest() {
		return false;
	}

	@Override
	public boolean refushRequest() {
		return false;
	}

}
