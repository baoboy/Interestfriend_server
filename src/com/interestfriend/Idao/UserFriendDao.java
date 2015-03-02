package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.UserFriend;

public interface UserFriendDao {
	boolean addUserFriend(UserFriend user);

	List<UserFriend> getUserFriendList(int user_id);
}
