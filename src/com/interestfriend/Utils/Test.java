package com.interestfriend.Utils;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.bean.Members;
import com.interestfriend.factory.MembersDaoFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MembersDao dao = MembersDaoFactory.getInstance();
		for (int i = 0; i < 2000; i++) {
			Members member = new Members();
			member.setCircle_id(3);
			member.setUser_id(8);
			member.setCircle_last_request_time(DateUtils.getLastUpdateTime());
			member.setUser_update_time(DateUtils.getLastUpdateTime());
			boolean rt = dao.addMembers(member);
		}
		System.out.println("finish");
	}

}
