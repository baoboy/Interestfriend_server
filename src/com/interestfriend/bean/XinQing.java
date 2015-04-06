package com.interestfriend.bean;

import java.util.ArrayList;
import java.util.List;

public class XinQing {
	private int xinqing_id = 0;
	private int publisher_id = 0;// 发布者id
	private String content = "";// 内容
	private String publish_time = ""; // 发布时间
	private String publisher_name = "";
	private String publisher_avatar = "";
	private String image_url = "";
	private List<XinQingPraise> praises = new ArrayList<XinQingPraise>();
	private List<XinQingComment> comments = new ArrayList<XinQingComment>();
	private int isPraise;// 1 赞 0未赞

	public int getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}

	public List<XinQingPraise> getPraises() {
		return praises;
	}

	public void setPraises(List<XinQingPraise> praises) {
		this.praises = praises;
	}

	public List<XinQingComment> getComments() {
		return comments;
	}

	public void setComments(List<XinQingComment> comments) {
		this.comments = comments;
	}

	public int getXinqing_id() {
		return xinqing_id;
	}

	public void setXinqing_id(int xinqing_id) {
		this.xinqing_id = xinqing_id;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
