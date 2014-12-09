package com.interestfriend.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

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
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4159610));
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4240610));
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4639200));
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4639610));
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4659610));
		System.out.println(getDistanceOfMeter(39.914994, 116.455932,
				39.9160140, 116.4659910));
		// System.out.println(getDistanceOfMeter(39.914994, 116.455932,
		// 39.9160140, 116.4679610));
		// System.out.println(getDistanceOfMeter(39.914994, 116.455932,
		// 39.9160140, 116.4759610));
		// System.out.println(getDistanceOfMeter(39.914994, 116.455932,
		// 39.9160140, 116.4989610));
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(Math.sqrt(39.9160140 + 116.4159610));
		System.out.println(Math.sqrt(39.9160140 + 116.4240610));
		System.out.println(Math.sqrt(39.9160140 + 116.4639200));
		System.out.println(Math.sqrt(39.9160140 + 116.4639610));
		System.out.println(Math.sqrt(39.9160140 + 116.4659610));
		System.out.println(Math.sqrt(39.9160140 + 116.4659910));
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
	// public static double getDistanceOfMeter(double lat1, double lng1,
	// double lat2, double lng2) {
	// double radLat1 = rad(lat1);
	// double radLat2 = rad(lat2);
	// double a = radLat1 - radLat2;
	// double b = rad(lng1) - rad(lng2);
	// double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
	// + Math.cos(radLat1) * Math.cos(radLat2)
	// * Math.pow(Math.sin(b / 2), 2)));
	// s = s * EARTH_RADIUS;
	// s = Math.round(s * 10000) / 10;
	// return s;
	// }

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistanceOfMeter(double lat1, double lon1,
			double lat2, double lon2) {
		// Based on http://www.ngs.noaa.gov/PUBS_LIB/inverse.pdf
		// using the "Inverse Formula" (section 4)

		int MAXITERS = 20;
		// Convert lat/long to radians
		lat1 *= Math.PI / 180.0;
		lat2 *= Math.PI / 180.0;
		lon1 *= Math.PI / 180.0;
		lon2 *= Math.PI / 180.0;

		double a = 6378137.0; // WGS84 major axis
		double b = 6356752.3142; // WGS84 semi-major axis
		double f = (a - b) / a;
		double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);

		double L = lon2 - lon1;
		double A = 0.0;
		double U1 = Math.atan((1.0 - f) * Math.tan(lat1));
		double U2 = Math.atan((1.0 - f) * Math.tan(lat2));

		double cosU1 = Math.cos(U1);
		double cosU2 = Math.cos(U2);
		double sinU1 = Math.sin(U1);
		double sinU2 = Math.sin(U2);
		double cosU1cosU2 = cosU1 * cosU2;
		double sinU1sinU2 = sinU1 * sinU2;

		double sigma = 0.0;
		double deltaSigma = 0.0;
		double cosSqAlpha = 0.0;
		double cos2SM = 0.0;
		double cosSigma = 0.0;
		double sinSigma = 0.0;
		double cosLambda = 0.0;
		double sinLambda = 0.0;

		double lambda = L; // initial guess
		for (int iter = 0; iter < MAXITERS; iter++) {
			double lambdaOrig = lambda;
			cosLambda = Math.cos(lambda);
			sinLambda = Math.sin(lambda);
			double t1 = cosU2 * sinLambda;
			double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
			double sinSqSigma = t1 * t1 + t2 * t2; // (14)
			sinSigma = Math.sqrt(sinSqSigma);
			cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda; // (15)
			sigma = Math.atan2(sinSigma, cosSigma); // (16)
			double sinAlpha = (sinSigma == 0) ? 0.0 : cosU1cosU2 * sinLambda
					/ sinSigma; // (17)
			cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
			cos2SM = (cosSqAlpha == 0) ? 0.0 : cosSigma - 2.0 * sinU1sinU2
					/ cosSqAlpha; // (18)

			double uSquared = cosSqAlpha * aSqMinusBSqOverBSq; // defn
			A = 1
					+ (uSquared / 16384.0)
					* // (3)
					(4096.0 + uSquared
							* (-768 + uSquared * (320.0 - 175.0 * uSquared)));
			double B = (uSquared / 1024.0) * // (4)
					(256.0 + uSquared
							* (-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
			double C = (f / 16.0) * cosSqAlpha
					* (4.0 + f * (4.0 - 3.0 * cosSqAlpha)); // (10)
			double cos2SMSq = cos2SM * cos2SM;
			deltaSigma = B
					* sinSigma
					* // (6)
					(cos2SM + (B / 4.0)
							* (cosSigma * (-1.0 + 2.0 * cos2SMSq) - (B / 6.0)
									* cos2SM
									* (-3.0 + 4.0 * sinSigma * sinSigma)
									* (-3.0 + 4.0 * cos2SMSq)));

			lambda = L
					+ (1.0 - C)
					* f
					* sinAlpha
					* (sigma + C
							* sinSigma
							* (cos2SM + C * cosSigma
									* (-1.0 + 2.0 * cos2SM * cos2SM))); // (11)

			double delta = (lambda - lambdaOrig) / lambda;
			if (Math.abs(delta) < 1.0e-12) {
				break;
			}
		}

		return b * A * (sigma - deltaSigma);
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	public static String getSMSCode() {
		Date d = new Date();
		long lseed = d.getTime();
		Random r = new Random(lseed); // 设置随机种子
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			str.append(r.nextInt(9)); // 生成随机数字
		}
		return str.toString();
	}

	public static void print(String str) {
		System.out.println(str);
	}

}
