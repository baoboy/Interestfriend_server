package com.interestfriend.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class DateUtils {

	private static DateFormat fileNameDF = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// �����ļ��ϴ��ļ���

	public static String getUpLoadFileName() {
		return fileNameDF.format(new Date());

	}

}