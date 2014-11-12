package com.interestfriend.enums;

import java.util.HashMap;
import java.util.Map;

public enum CircleStatus {
	DEL, UPDATE, INVALID;

	public static Map<String, CircleStatus> s2s = new HashMap<String, CircleStatus>();
	static {
		for (CircleStatus s : CircleStatus.values()) {
			s2s.put(s.name(), s);
		}
	}

	public static CircleStatus convert(String s) {
		if (s2s.containsKey(s)) {
			return s2s.get(s);
		}
		return CircleStatus.INVALID;
	}
}
