package com.interestfriend.huanxin;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * REST API Demo: 发送消息REST API httpclient实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/sendmessage.html
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobSendMessage {
	public static void sendGroupMessage(String group_id, String publisher_id) {
		String token = "";
		// 获取token
		try {
			token = getAccessToken(EasemobConstans.APP_KEY,
					EasemobConstans.USER_NAME, EasemobConstans.PASSWORD);
			System.out.println("token:" + token);
			// 发送Text消息
			List<String> toUsernames = new ArrayList<String>();
			toUsernames.add(group_id);
			String fromUser = "growth";
			String txtContent = "Hello,It is a test message!";
			Map<String, String> sendResult;
			sendResult = sendTextMessage(EasemobConstans.APP_KEY, token,
					txtContent, fromUser, toUsernames, publisher_id);
			for (String toUsername : toUsernames) {
				String isSuccess = sendResult.get(toUsername);
				if (isSuccess.equals("success")) {
					System.out.println("send message to " + toUsername
							+ " success!");
				} else {
					System.out.println("send message to " + toUsername
							+ " fail!");
				}
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendGroupMessage("1412996732230555", "");
	}

	/**
	 * 发送文本消息
	 * 
	 * @param textContent
	 *            消息内容
	 * @param username
	 *            发送人
	 * @return true发送成功 false 发送失败
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static Map<String, String> sendTextMessage(String appKey,
			String token, String textContent, String fromUser,
			List<String> toUsernames, String publisher_id) throws IOException,
			KeyManagementException, NoSuchAlgorithmException {
		String httpUrl = "https://a1.easemob.com/"
				+ appKey.replaceFirst("#", "/") + "/messages";
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("target_type", "chatgroups");
		body.put("target", toUsernames);
		Map<String, String> msgBody = new HashMap<String, String>();
		msgBody.put("type", "txt");
		msgBody.put("msg", "hello from rest");
		body.put("msg", msgBody);
		body.put("from", fromUser);
		Map<String, String> extBody = new HashMap<String, String>();
		extBody.put("publisher_id", publisher_id);
		// extBody.put("attr2", "v2");
		body.put("ext", extBody);
		ObjectMapper mapper = new ObjectMapper();
		Client client = getClient(true);
		WebTarget target = ((javax.ws.rs.client.Client) client).target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Bearer " + token)
				.buildPost(Entity.json(body)).invoke();
		String resultMsg = response.readEntity(String.class);
		String content = mapper.readTree(resultMsg).get("data").toString();
		Map<String, String> result = mapper.readValue(content, Map.class);
		System.out.println("resultMsg:" + resultMsg);
		return result;
	}

	/**
	 * 获取用户token
	 * 
	 * @param appKey
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String getAccessToken(String appKey, String username,
			String password) throws IOException, KeyManagementException,
			NoSuchAlgorithmException {
		String host = "https://a1.easemob.com";
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		Client client = getClient(true);
		WebTarget target = ((javax.ws.rs.client.Client) client).target(host)
				.path("/{org}/{app}/token").resolveTemplate("org", orgName)
				.resolveTemplate("app", appName);
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("grant_type", "password");
		payload.put("username", username);
		payload.put("password", password);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.buildPost(Entity.json(payload)).invoke();
		String result = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		String token = objectMapper.readTree(result).get("access_token")
				.asText();
		return token;

	}

	private static Client getClient(boolean https)
			throws KeyManagementException, NoSuchAlgorithmException {
		ClientBuilder builder = ClientBuilder.newBuilder();
		if (https) {
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
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

}
