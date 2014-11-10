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
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String addSQL = "insert into qequest_join_circle(join_circle_id,join_circle_creator_id,request_join_circle_user_id) values(?,?,?)";

		PreparedStatement pstmt = null; // ����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL); // ���Ԥ������󲢸�ֵ
			pstmt.setInt(1, joinCircle.getJoin_circle_id());
			pstmt.setInt(2, joinCircle.getJoin_circle_creator_id());// ���õڶ�������
			pstmt.setInt(3, joinCircle.getRequest_join_circle_user_id());
			int count = pstmt.executeUpdate(); // ִ�и���
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // �ر�Ԥ�������
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
