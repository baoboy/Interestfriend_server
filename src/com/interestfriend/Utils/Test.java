package com.interestfriend.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	static List<User> lists1 = new ArrayList<User>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.name = "bb";
		lists1.add(user);
		for (User u : lists1) {
			delById();
			lists1.add(u);
			lists1.add(u);

		}
		System.out.println(lists1);
	}

	public static void delById() {
		for (Iterator<User> it = lists1.iterator(); it.hasNext();) {
			if (it.next().name.equals("bb")) {
				it.remove();
				break;
			}
		}
	}

	public static class User {
		String name;

		@Override
		public String toString() {
			return "name:" + name;
		}
	}
}
