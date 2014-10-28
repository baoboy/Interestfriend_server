package com.interestfriend.bean;

public class Members {
	private int user_id;
	private int circle_id;
	private long user_update_time = 0l;
	private String user_state = "ADD";// add / del /update

	public long getUser_update_time() {
		return user_update_time;
	}

	public void setUser_update_time(long user_update_time) {
		this.user_update_time = user_update_time;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

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

}
