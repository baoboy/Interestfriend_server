package com.interestfriend.bean;

import java.util.ArrayList;
import java.util.List;

public class Video {

	private String video_img = "";
	private String video_path = "";
	public int video_size;
	public int video_duration;
	private int direct = 1;// 1 send 2 receive
	private int cid = 0;
	private int publisher_id = 0;
	private int video_id;
	private String time = "";
	private String publisher_name = "";
	private String publisher_avatar = "";
	private List<VideoComment> comments = new ArrayList<VideoComment>();
	private String video_content = "";

	public String getVideo_content() {
		return video_content;
	}

	public void setVideo_content(String video_content) {
		this.video_content = video_content;
	}

	public List<VideoComment> getComments() {
		return comments;
	}

	public void setComments(List<VideoComment> comments) {
		this.comments = comments;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public String getPublisher_avatar() {
		return publisher_avatar;
	}

	public void setPublisher_avatar(String publisher_avatar) {
		this.publisher_avatar = publisher_avatar;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public String getVideo_img() {
		return video_img;
	}

	public void setVideo_img(String video_img) {
		this.video_img = video_img;
	}

	public String getVideo_path() {
		return video_path;
	}

	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}

	public int getVideo_size() {
		return video_size;
	}

	public void setVideo_size(int video_size) {
		this.video_size = video_size;
	}

	public int getVideo_duration() {
		return video_duration;
	}

	public void setVideo_duration(int video_duration) {
		this.video_duration = video_duration;
	}

}
