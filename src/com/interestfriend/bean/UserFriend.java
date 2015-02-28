package com.interestfriend.bean;

public class UserFriend {
	private int user_id;
	private int user_friend_id;
	private String user_friend_name = "";
	private String user_friend_avatar = "";
	private String user_friend_chat_id = "";
	private String user_friend_circle = "";
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_friend_id() {
		return user_friend_id;
	}
	public void setUser_friend_id(int user_friend_id) {
		this.user_friend_id = user_friend_id;
	}
	public String getUser_friend_name() {
		return user_friend_name;
	}
	public void setUser_friend_name(String user_friend_name) {
		this.user_friend_name = user_friend_name;
	}
	public String getUser_friend_avatar() {
		return user_friend_avatar;
	}
	public void setUser_friend_avatar(String user_friend_avatar) {
		this.user_friend_avatar = user_friend_avatar;
	}
	public String getUser_friend_chat_id() {
		return user_friend_chat_id;
	}
	public void setUser_friend_chat_id(String user_friend_chat_id) {
		this.user_friend_chat_id = user_friend_chat_id;
	}
	public String getUser_friend_circle() {
		return user_friend_circle;
	}
	public void setUser_friend_circle(String user_friend_circle) {
		this.user_friend_circle = user_friend_circle;
	}
}
