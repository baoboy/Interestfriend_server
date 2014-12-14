package com.interestfriend.Utils;

import java.util.ArrayList;
import java.util.List;

import com.interestfriend.bean.CategoryCircle;

public class CategoryCircleUtils {
	public static List<CategoryCircle> lists = new ArrayList<CategoryCircle>();
	public static final String URL = "http://123.56.46.254:8080/InterestFriend/images/category/";
	static {
		CategoryCircle c = new CategoryCircle();
		c.setCode(1);
		c.setName("官方圈子");
		c.setLogo(URL + "guanfang.jpg");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(2);
		c.setName("游戏世界");
		c.setLogo(URL + "game.jpg");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(3);
		c.setLogo(URL + "cartoon.jpg");
		c.setName("卡通动漫");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(4);
		c.setLogo(URL + "mingxing.jpg");
		c.setName("明星名人");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(5);
		c.setLogo(URL + "dianying.jpg");
		c.setName("电影电视");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(6);
		c.setLogo(URL + "shenghuo.jpg");
		c.setName("生活情感");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(7);
		c.setLogo(URL + "tiyuyundong.jpg");
		c.setName("体育运动");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(8);
		c.setLogo(URL + "wenxueyishu.jpg");
		c.setName("文学艺术");
		lists.add(c);
		c = new CategoryCircle();
		c.setCode(9);
		c.setLogo(URL + "sheying.jpg");
		c.setName("旅游摄影");
		lists.add(c);
	}

	public static String getCateGoryNameByCode(int code) {
		for (CategoryCircle cate : lists) {
			if (cate.getCode() == code) {
				return cate.getName();
			}
		}
		return "其他圈子";
	}
}
