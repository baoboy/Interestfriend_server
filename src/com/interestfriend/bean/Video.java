package com.interestfriend.bean;

public class Video {

	private String video_img = "";
	private String video_path = "";
	public int video_size;
	public int video_duration;
	private int direct = 1;// 1 send 2 receive
	private int cid = 0;
	private int publisher_id = 0;
	private int video_id;

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
