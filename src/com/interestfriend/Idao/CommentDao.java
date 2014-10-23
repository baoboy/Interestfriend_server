package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.Comment;

public interface CommentDao {
	boolean insertComment(Comment comment);

	List<Comment> getCommentByGrowthID(int growth_id);
}
