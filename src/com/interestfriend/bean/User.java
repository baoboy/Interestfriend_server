package com.interestfriend.bean;

public class User {
	private int userID = 0;// 用户id
	private String userName = "";// 用户姓名
	private String userCellPhone = "";// 用户电话
	private String userAvatar = "";// 用户注册头像
	private String userGender = "";// 用户注册性别
	private String userBirthday = "";// 用户注册生日
	private String userPassword = "";// 用户注册密码
	private String userRegisterTime = "";
	private String userChatId = "";
	private String sortKey = "";
	private String pinYinFir = "";
	private long userLastUpdateTime = 0l;
	private String userState = "add";// add / del /update

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getPinYinFir() {
		return pinYinFir;
	}

	public void setPinYinFir(String pinYinFir) {
		this.pinYinFir = pinYinFir;
	}

	public long getUserLastUpdateTime() {
		return userLastUpdateTime;
	}

	public void setUserLastUpdateTime(long userLastUpdateTime) {
		this.userLastUpdateTime = userLastUpdateTime;
	}

	public String getUserChatId() {
		return userChatId;
	}

	public void setUserChatId(String userChatId) {
		this.userChatId = userChatId;
	}

	public String getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(String userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

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
