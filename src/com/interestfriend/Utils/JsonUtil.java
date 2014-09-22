package com.interestfriend.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	/**
	 * ����json�ַ���
	 * 
	 * ��ʽ: {"err":"USER_EXIST","rt":1}
	 * 
	 * @param parms
	 * @return
	 */
	public static String toJsonString(Map<String, Object> parms) {
		JSONObject json = new JSONObject();
		Iterator<?> iterator = parms.entrySet().iterator();
		try {
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (key != null && value != null) {
					json.put(key.toString(), value.toString());
				}
			}
		} catch (JSONException e) {
			return e.toString();
		}
		return json.toString();

	}

	/**
	 * list����ת����json����
	 */
	public static String listToJsonArray(String key, Object b) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, b);
		JSONObject json = new JSONObject();
		try {
			json.put("lists", map);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();

	}
}
