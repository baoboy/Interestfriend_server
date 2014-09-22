package com.interestfriend.bean;

public class User {
	private int userID = 0;// 用户id
	private String userName = "";// 用户姓名
	private String userCellPhone = "";// 用户电话
	private String userAvatar = "";// 用户注册头像
	private String userGender = "";// 用户注册性别
	private String userBirthday = "";// 用户注册生日
	private String userPassword = "";// 用户注册密码

	public User() {
	}

	public User(String userName, String userCellPhone) {
		this.userName = userName;
		this.userCellPhone = userCellPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCellPhone() {
		return userCellPhone;
	}

	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	@Override
	public String toString() {
		return "user_name:" + userName + " user_cellphone:" + userCellPhone
				+ "  user_gender:" + userGender + " user_password:"
				+ userPassword + "  user_birthday:" + userBirthday;
	}
}
