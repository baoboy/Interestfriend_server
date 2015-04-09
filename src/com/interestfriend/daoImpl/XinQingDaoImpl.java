package com.interestfriend.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interestfriend.Idao.XinQingCommentDao;
import com.interestfriend.Idao.XinQingDao;
import com.interestfriend.Idao.XinQingPraiseDao;
import com.interestfriend.bean.XinQing;
import com.interestfriend.bean.XinQingPraise;
import com.interestfriend.db.DBConnection;
import com.interestfriend.factory.XinQingCommentDaoFactory;
import com.interestfriend.factory.XinQingPraiseDaoFactory;

public class XinQingDaoImpl implements XinQingDao {

	@Override
	public int insertToDB(XinQing xinqing) {
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int autoIncKeyFromApi = -1;

		String sql = "insert into xinqing(publisher_id,content,publish_time,image_url) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing.getPublisher_id());
			pstmt.setString(2, xinqing.getContent().replace("'", ""));
			pstmt.setString(3, xinqing.getPublish_time());
			pstmt.setString(4, xinqing.getImage_url());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); // ��ȡ����������
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
	public List<XinQing> getXinQingList(int refushState, String refushTime,
			int user_id) {
		List<XinQing> lists = new ArrayList<XinQing>();
		Connection conn = DBConnection.getConnection();
		ResultSet res = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if (refushState == 1) {
			sql = "select xinqing.*,`user`.user_avatar,`user`.user_name  from  xinqing  INNER JOIN  `user`  on  xinqing.publisher_id =`user`.user_id where publish_time>?  order by publish_time desc limit 0,20";
		} else {
			sql = "select xinqing.*,`user`.user_avatar,`user`.user_name  from  xinqing  INNER JOIN  `user`  on  xinqing.publisher_id =`user`.user_id  where publish_time <?   order by publish_time desc limit 0,20";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, refushTime);
			res = pstmt.executeQuery();
			XinQingCommentDao cDao = XinQingCommentDaoFactory.getInstance();
			XinQingPraiseDao pDao = XinQingPraiseDaoFactory.getInstance();
			while (res.next()) {
				XinQing xin = new XinQing();
				int xinqing_id = res.getInt("xinqing_id");
				xin.setXinqing_id(xinqing_id);
				xin.setContent(res.getString("content"));
				xin.setImage_url(res.getString("image_url"));
				xin.setPublish_time(res.getString("publish_time"));
				xin.setPublisher_avatar(res.getString("user_avatar"));
				xin.setPublisher_name(res.getString("user_name"));
				xin.setPublisher_id(res.getInt("publisher_id"));
				xin.setComments(cDao.getCommentByXinQingID(xinqing_id));
				xin.setPraises(pDao.findPraiseUserByXinQingID(xinqing_id));
				XinQingPraise praise = new XinQingPraise();
				praise.setUser_id(user_id);
				praise.setXinqing_id(xinqing_id);
				xin.setIsPraise(pDao.findPraiseByUserID(praise));

				lists.add(xin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public XinQing getXinQingByID(int xinqing_id, int user_id) {
		Connection conn = DBConnection.getConnection();
		ResultSet res = null;
		PreparedStatement pstmt = null;
		String sql = "select xinqing.*,`user`.user_avatar,`user`.user_name  from  xinqing  INNER JOIN  `user`  on  xinqing.publisher_id =`user`.user_id  where xinqing_id=?";
		XinQing xinqing = new XinQing();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, xinqing_id);
			res = pstmt.executeQuery();
			XinQingCommentDao cDao = XinQingCommentDaoFactory.getInstance();
			XinQingPraiseDao pDao = XinQingPraiseDaoFactory.getInstance();
			while (res.next()) {
				xinqing.setXinqing_id(xinqing_id);
				xinqing.setContent(res.getString("content"));
				xinqing.setImage_url(res.getString("image_url"));
				xinqing.setPublish_time(res.getString("publish_time"));
				xinqing.setPublisher_avatar(res.getString("user_avatar"));
				xinqing.setPublisher_name(res.getString("user_name"));
				xinqing.setPublisher_id(res.getInt("publisher_id"));
				xinqing.setComments(cDao.getCommentByXinQingID(xinqing_id));
				xinqing.setPraises(pDao.findPraiseUserByXinQingID(xinqing_id));
				XinQingPraise praise = new XinQingPraise();
				praise.setUser_id(user_id);
				praise.setXinqing_id(xinqing_id);
				xinqing.setIsPraise(pDao.findPraiseByUserID(praise));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return xinqing;
	}
}
