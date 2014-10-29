package com.interestfriend.bean;

public class Circle {
	private int creator_id = 0;
	private int circle_id = 0;// 圈子ID
	private String circle_name = "";
	private String circle_description = "";
	private String circle_avatar = "";
	private String group_id = "";
	private int category;
	private double longitude;// 经度
	private double latitude; // 维度
	private int distance;
	private String circle_creator_name = "";
	private String circle_create_time = "";
	private String circle_category = "";

	public String getCircle_category() {
		return circle_category;
	}

	public void setCircle_category(String circle_category) {
		this.circle_category = circle_category;
	}

	public String getCircle_creator_name() {
		return circle_creator_name;
	}

	public void setCircle_creator_name(String circle_creator_name) {
		this.circle_creator_name = circle_creator_name;
	}

	public String getCircle_create_time() {
		return circle_create_time;
	}

	public void setCircle_create_time(String circle_create_time) {
		this.circle_create_time = circle_create_time;
	}

	private String circle_state = "";

	public String getCircle_state() {
		return circle_state;
	}

	public void setCircle_state(String circle_state) {
		this.circle_state = circle_state;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public int getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "creator_id:" + this.creator_id + "  circle_id" + this.circle_id
				+ "  circle_name:" + this.circle_name + "  circle_description:"
				+ this.circle_description + "  circle_avatar:"
				+ this.circle_avatar;
	}
}
