package com.interestfriend.Idao;

import com.interestfriend.bean.UserFriendInviteMessage;

public interface UserFriendInviteMessageDao {
	boolean addMessage(UserFriendInviteMessage message);

	boolean delMessage(UserFriendInviteMessage message);

	boolean getMessage(UserFriendInviteMessage message);
}
