package com.interestfriend.huanxin;

import java.net.URL;

/**
 * HTTPClient EndPoints
 * 
 * @author Lynch 2014-09-15
 * 
 */
public interface EndPoints {

	public static final URL ROOT_URL = HttpsUtils.getURL("");

	public static final URL MANAGEMENT_URL = HttpsUtils.getURL("/management");

	public static final URL TOKEN_ORG_URL = HttpsUtils
			.getURL("/management/token");

	public static final URL APPLICATION_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/"));

	public static final URL TOKEN_APP_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/token");

	public static final URL USERS_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/users");

	public static final URL MESSAGES_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/messages");

	public static final URL CHATMESSAGES_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/chatmessages");

	public static final URL CHATGROUPS_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/chatgroups");

	public static final URL CHATFILES_URL = HttpsUtils
			.getURL(EasemobConstans.APPKEY.replace("#", "/") + "/chatfiles");

}
