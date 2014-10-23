package com.interestfriend.bean;

import java.util.ArrayList;
import java.util.List;

public class Growth {
	private int growth_id = 0;
	private int cid = 0;
	private int publisher_id = 0;
	private String time = "";
	private String content = "";
	private List<GrowthImage> images = new ArrayList<GrowthImage>();
	private List<Comment> comments = new ArrayList<Comment>();

	public int getGrowth_id() {
		return growth_id;
	}

	public void setGrowth_id(int growth_id) {
		this.growth_id = growth_id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<GrowthImage> getImages() {
		return images;
	}

	public void setImages(List<GrowthImage> images) {
		this.images = images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "gid:" + this.growth_id + "  content:" + this.content
				+ "  images:" + this.images;
	}
}
