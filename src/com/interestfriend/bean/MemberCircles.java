package com.interestfriend.bean;

public class MemberCircles {
	private int user_id;
	private int circle_id;
	private long circle_last_request_time;
	private String circle_state = "";

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCircle_id() {
		return circle_id;
	}

	public void setCircle_id(int circle_id) {
		this.circle_id = circle_id;
	}

	public long getCircle_last_request_time() {
		return circle_last_request_time;
	}

	public void setCircle_last_request_time(long circle_last_request_time) {
		this.circle_last_request_time = circle_last_request_time;
	}

	public String getCircle_state() {
		return circle_state;
	}

	public void setCircle_state(String circle_state) {
		this.circle_state = circle_state;
	}
}
