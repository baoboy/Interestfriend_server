package com.interestfriend.Utils;

import java.io.IOException;

public class Utils {
	public static void getVideoImage(String videoPath, String saveImagePath) {
		System.out.println(saveImagePath);
		System.out.println(videoPath);

		try {
			// 调用批处理文件
			Runtime.getRuntime().exec(
					"cmd /c start E://ffmpeg-20131021//ffmpeg//bin//ffmpeg.bat "
							+ videoPath + " " + saveImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		getVideoImage(
				"E:\\tomcat7\\apache-tomcat-7.0.52\\webapps\\InterestFriend\\video\\2014-10-15-19-55-01.3gp",
				"E:\\tomcat7\\apache-tomcat-7.0.52\\webapps\\InterestFriend\\video-image\\2014-10-15-19-55-01.jpg");
	}
}
