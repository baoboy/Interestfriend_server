package com.interestfriend.Utils;

import java.io.IOException;

public class Utils {
	public static void getVideoImage(String videoPath, String saveImagePath) {
		try {
			// �����������ļ�
			Runtime.getRuntime().exec(
					"cmd /c start E://ffmpeg-20131021//ffmpeg//bin//ffmpeg.bat "
							+ videoPath + " " + saveImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
