package com.interestfriend.bean;

public class Comment {
	private int comment_id;
	private int growth_id;
	private int publisher_id;
	private String comment_content = "";
	private String comment_time = "";

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getGrowth_id() {
		return growth_id;
	}

	public void setGrowth_id(int growth_id) {
		this.growth_id = growth_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

}
