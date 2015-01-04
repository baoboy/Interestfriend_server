package com.interestfriend.huanxin;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.interestfriend.Utils.MD5;
import com.interestfriend.Utils.Utils;

/**
 * REST API Demo : �û�����REST API HttpClient4.3ʵ��
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * 
 * @author Lynch 2014-07-09
 * 
 */
public class EasemobUserAPI {

	/**
	 * �����û�
	 * 
	 * @param host
	 *            IP��������
	 * @param port
	 *            �˿�
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param postBody
	 *            ��װ���û����Ե�json����
	 * @param token
	 *            admin����token
	 * @return
	 */
	public static String createNewUser(String host, String appKey,
			Map<String, Object> body, String token) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users";

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token,
				HttpsUtils.Map2Json(body), HttpsUtils.Method_POST);
		return result;
	}

	/**
	 * ɾ���û�
	 * 
	 * @param host
	 *            IP��������
	 * @param port
	 *            �˿�
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param id
	 *            usename or uuid
	 * @param token
	 *            admin����token
	 * @return
	 */
	public static String deleteUser(String host, String appKey, String id,
			String token) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users/" + id;

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, null,
				HttpsUtils.Method_DELETE);

		return result;
	}

	/**
	 * ��ȡtoken
	 * 
	 * @param host
	 *            IP��������
	 * @param port
	 *            �˿�
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param isAdmin
	 *            org����Աtoken true, app����Աtoken false, IM�û�token false
	 * @param postBody
	 *            POST������
	 * @return
	 */
	public static String getAccessToken(String host, String appKey,
			Boolean isAdmin, Map<String, Object> postBody) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String accessToken = "";
		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, null,
				HttpsUtils.Map2Json(postBody), HttpsUtils.Method_POST);
		Map<String, String> resultMap = HttpsUtils.Json2Map(result);

		accessToken = resultMap.get("access_token");

		return accessToken;
	}

	public static String getOrgToken() {
		String host = "a1.easemob.com";
		// ��ȡorg����Աtoken
		Map<String, Object> getAccessTokenPostBody = new HashMap<String, Object>();
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", EasemobConstans.USER_NAME);
		getAccessTokenPostBody.put("password", EasemobConstans.PASSWORD);
		String admin_token = EasemobUserAPI.getAccessToken(host,
				EasemobConstans.APP_KEY, false, getAccessTokenPostBody);
		return admin_token;
	}

	public static void main(String[] args) {
		String host = "a1.easemob.com";
		String appKey = "774663576#interestfriend";

		// ��ȡIM�û�token
		Map<String, Object> getIMAccessTokenPostBody = new HashMap<String, Object>();
		getIMAccessTokenPostBody.put("grant_type", "password");
		getIMAccessTokenPostBody.put("username", "binbin");
		getIMAccessTokenPostBody.put("password", "binbin521");
		String imToken = EasemobUserAPI.getAccessToken(host, appKey, false,
				getIMAccessTokenPostBody);
		System.out.println("token:;" + imToken);

		// ��ȡorg����Աtoken
		Map<String, Object> getAccessTokenPostBody = new HashMap<String, Object>();
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", "binbin");
		getAccessTokenPostBody.put("password", "binbin521");
		String orgAdminToken = EasemobUserAPI.getAccessToken(host, appKey,
				false, getAccessTokenPostBody);
		System.out.println(orgAdminToken);

		/**
		 * ����IM�û����� �ṩ����Աtoken
		 */
		String username = "18560133195";
		ObjectNode json2 = JsonNodeFactory.instance.objectNode();
		json2.put("newpassword", MD5.Md5("1234567"));
		ObjectNode modifyIMUserPasswordWithAdminTokenNode = modifyIMUserPasswordWithAdminToken(
				username, json2);
		if (null != modifyIMUserPasswordWithAdminTokenNode) {
			Utils.print("����IM�û����� �ṩ����Աtoken: "
					+ modifyIMUserPasswordWithAdminTokenNode.toString());
		}
	}

	public static void createNewUser(String username, String password) {
		// ��ȡorg����Աtoken
		Map<String, Object> getAccessTokenPostBody = new HashMap<String, Object>();
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", EasemobConstans.USER_NAME);
		getAccessTokenPostBody.put("password", EasemobConstans.PASSWORD);
		String orgAdminToken = EasemobUserAPI.getAccessToken(
				EasemobConstans.HOST, EasemobConstans.APP_KEY, false,
				getAccessTokenPostBody);
		// �����û�
		Map<String, Object> createNewUserPostBody = new HashMap<String, Object>();
		createNewUserPostBody.put("username", username);
		createNewUserPostBody.put("password", password);
		EasemobUserAPI.createNewUser(EasemobConstans.HOST,
				EasemobConstans.APP_KEY, createNewUserPostBody, orgAdminToken);

	}

	/**
	 * ����IM�û����� �ṩ����Աtoken
	 * 
	 * @param userPrimaryKey
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithAdminToken(
			String userPrimaryKey, ObjectNode dataObjectNode) {
		JsonNodeFactory factory = new JsonNodeFactory(false);
		ObjectNode objectNode = factory.objectNode();
		// ͨ��app��client_id��client_secret����ȡapp����Աtoken
		Credential credential = new ClientSecretCredential(
				EasemobConstans.APP_CLIENT_ID,
				EasemobConstans.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);
		// check Constants.APPKEY format
		if (!HttpsUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstans.APP_KEY)) {
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		if (StringUtils.isEmpty(userPrimaryKey)) {
			objectNode
					.put("message",
							"Property that named userPrimaryKey must be provided��the value is username or uuid of imuser.");
			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			objectNode.put("message",
					"Property that named newpassword must be provided .");
			return objectNode;
		}

		try {
			URL modifyIMUserPasswordWithAdminTokenUrl = HttpsUtils
					.getURL(EasemobConstans.APP_KEY.replace("#", "/")
							+ "/users/" + userPrimaryKey + "/password");
			objectNode = HttpsUtils.sendHTTPRequest(
					modifyIMUserPasswordWithAdminTokenUrl, credential,
					dataObjectNode, HTTPMethod.METHOD_PUT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}
