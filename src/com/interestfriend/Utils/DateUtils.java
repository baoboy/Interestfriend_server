package com.interestfriend.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class DateUtils {

	private static DateFormat fileNameDF = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// 定义文件上传文件名

	public static String getUpLoadFileName() {
		return fileNameDF.format(new Date());

	}

	public static String getRegisterTime() {
		DateFormat fileNameDF = new SimpleDateFormat("yyyy-MM-dd");
		return fileNameDF.format(new Date());

	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(getUpLoadFileName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}