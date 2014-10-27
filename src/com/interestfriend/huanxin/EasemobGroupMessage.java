package com.interestfriend.huanxin;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 群聊天信息和用户管理
 * 
 * @author Administrator
 * 
 */
public class EasemobGroupMessage {
	public static String createCircleGroup(String circleName,
			String circleDescription) {
		String groupid = "";
		String token = EasemobUserAPI.getOrgToken();
		List<String> list = new ArrayList<String>();
		try {
			groupid = creatChatGroups(EasemobConstans.APP_KEY, token,
					circleName, circleDescription, true,
					EasemobConstans.USER_NAME, list);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return groupid;
	}

	/**
	 * 创建群组
	 * 
	 * @param appkey
	 *            申请到的appkey
	 * @param groupname
	 *            群组的名称
	 * @param desc
	 *            群组的描述
	 * @param publicParams
	 *            是否是公开群
	 * @param owner
	 *            群组创建者
	 * @return
	 * @throws JsonProcessingException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String creatChatGroups(String appkey, String admin_token,
			String groupname, String desc, boolean publicParams, String owner,
			List<String> members) throws JsonProcessingException,
			KeyManagementException, NoSuchAlgorithmException {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups";
		Map<String, Object> paramsBody = new HashMap<String, Object>();
		paramsBody.put("groupname", groupname);
		paramsBody.put("desc", desc);
		paramsBody.put("owner", owner);
		paramsBody.put("public", publicParams);
		paramsBody.put("members", members);

		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Bearer " + admin_token)
				.buildPost(Entity.json(paramsBody)).invoke();
		String result = response.readEntity(String.class);
		return getGroupID(result);
	}

	private static String getGroupID(String result) {
		String groupid = "";
		try {
			JSONObject object = new JSONObject(result);
			JSONObject j = object.getJSONObject("data");
			groupid = j.getString("groupid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return groupid;
	}

	private static Client getClient(boolean https)
			throws KeyManagementException, NoSuchAlgorithmException {
		ClientBuilder builder = ClientBuilder.newBuilder();
		if (https) {
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					System.out.println("Warning: URL Host: " + hostname
							+ " vs. " + session.getPeerHost());
					return true;
				}
			};

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");

			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			builder.sslContext(sc).hostnameVerifier(hv);
		}
		return builder.build();

	}

	public static void addUserToGroup(String groupid, String username) {
		String token = EasemobUserAPI.getOrgToken();
		addUserToGroup(EasemobConstans.APP_KEY, token, groupid, username);
	}

	/**
	 * 在群组中添加一个人
	 * 
	 * @param appkey
	 * @param admin_token
	 * @param groupid
	 * @param username
	 */
	public static void addUserToGroup(String appkey, String admin_token,
			String groupid, String username) {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid
				+ "/users/" + username;
		String result = HttpsUtils.sendSSLRequest(httpUrl, admin_token, "",
				HttpsUtils.Method_POST);

		System.out.println("result:" + result);

	}

	public static void deleteUserFromGroup(String appkey, String adminToken,
			String groupid, String username) {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid
				+ "/users/" + username;

		String result = HttpsUtils.sendSSLRequest(httpUrl, adminToken, "",
				HttpsUtils.Method_DELETE);
		System.out.println("result:" + result);
	}

}
