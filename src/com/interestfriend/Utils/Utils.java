package com.interestfriend.Utils;

import java.io.IOException;

public class Utils {

	/**
	 * 地球半径：6378.137KM
	 */
	private static double EARTH_RADIUS = 6378.137;

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
		// getVideoImage(
		// "E:\\tomcat7\\apache-tomcat-7.0.52\\webapps\\InterestFriend\\video\\2014-10-15-19-55-01.3gp",
		// "E:\\tomcat7\\apache-tomcat-7.0.52\\webapps\\InterestFriend\\video-image\\2014-10-15-19-55-01.jpg");
		System.out.println(getDistanceOfMeter(39.91181976027792,
				116.45035017955685, 39.92979023972208, 116.47378182044314));
	}

	/**
	 * 获取指定经纬度 半径 周围的点
	 * 
	 * @param raidus
	 *            单位米 return minLat,minLng,maxLat,maxLng
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;
		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLong = longitude - radiusLng;
		Double maxLong = longitude + radiusLng;
		return new double[] { minLat, minLong, maxLat, maxLong };
	}

	/**
	 * 得到两点间的距离 米
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistanceOfMeter(double lat1, double lng1,
			double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10;
		return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
