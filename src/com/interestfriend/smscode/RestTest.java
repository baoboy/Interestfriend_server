/**
 * @author Glan.duanyj
 * @date 2014-05-12
 * @project rest_demo
 */
package com.interestfriend.smscode;

import java.io.IOException;

public class RestTest {

	static AbsRestClient InstantiationRestAPI(boolean enable) {
		return new JsonReqClient();

	}

	public static void testTemplateSMS(boolean json, String accountSid,
			String authToken, String appId, String templateId, String to,
			String param) {
		try {
			String result = InstantiationRestAPI(json).templateSMS(accountSid,
					authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		String accountSid = "cea526c74f8f77af93613464d94da1e7";
		String token = "390b46431da96b0a0452f936701fc054";
		String appId = "f500ab6d729a490a9feae574a7c7a58c";
		String templateId = "1500";
		String to = "18560133195";
		String para = "1234";
		testTemplateSMS(true, accountSid, token, appId, templateId, to, para);
	}
}
