package com.interestfriend.bean;

public class Circle {
	private int user_id = 0;
	private int circle_id = 0;// È¦×ÓID
	private String circle_name = "";
	private String circle_description = "";
	private String circle_avatar = "";

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

	public String getCircle_name() {
		return circle_name;
	}

	public void setCircle_name(String circle_name) {
		this.circle_name = circle_name;
	}

	public String getCircle_description() {
		return circle_description;
	}

	public void setCircle_description(String circle_description) {
		this.circle_description = circle_description;
	}

	public String getCircle_avatar() {
		return circle_avatar;
	}

	public void setCircle_avatar(String circle_avatar) {
		this.circle_avatar = circle_avatar;
	}

	@Override
	public String toString() {
		return "user_id:" + this.user_id + "  circle_id" + this.circle_id
				+ "  circle_name:" + this.circle_name + "  circle_description:"
				+ this.circle_description + "  circle_avatar:"
				+ this.circle_avatar;
	}
}
