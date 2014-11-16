package com.interestfriend.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	ADD, UPDATE, INVALID;
	public static Map<String, Status> s2s = new HashMap<String, Status>();
	static {
		for (Status s : Status.values()) {
			s2s.put(s.name(), s);
		}
	}

	public static Status convert(String s) {
		if (s2s.containsKey(s)) {
			return s2s.get(s);
		}
		return Status.INVALID;
	}
}
