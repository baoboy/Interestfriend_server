package com.interestfriend.bean;

public class RequestJoinCircle {
	private int join_circle_id;// Ҫ�����Ȧ��id
	private int join_circle_creator_id;// Ҫ�����Ȧ�ӵĴ�����id
	private int request_join_circle_user_id;// �������Ȧ�ӵ��û�id

	public int getJoin_circle_id() {
		return join_circle_id;
	}

	public void setJoin_circle_id(int join_circle_id) {
		this.join_circle_id = join_circle_id;
	}

	public int getJoin_circle_creator_id() {
		return join_circle_creator_id;
	}

	public void setJoin_circle_creator_id(int join_circle_creator_id) {
		this.join_circle_creator_id = join_circle_creator_id;
	}

	public int getRequest_join_circle_user_id() {
		return request_join_circle_user_id;
	}

	public void setRequest_join_circle_user_id(int request_join_circle_user_id) {
		this.request_join_circle_user_id = request_join_circle_user_id;
	}

}
