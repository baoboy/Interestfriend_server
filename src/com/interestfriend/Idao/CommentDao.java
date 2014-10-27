package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.Comment;

public interface CommentDao {
	int insertComment(Comment comment);// ·µ»Øid

	List<Comment> getCommentByGrowthID(int growth_id);

	boolean deleteCommentByID(int comment_id);
}
