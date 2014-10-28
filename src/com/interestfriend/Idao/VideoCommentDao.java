package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.VideoComment;

public interface VideoCommentDao {
	int insertComment(VideoComment comment);

	List<VideoComment> getCommentByVideoID(int video_id);

	boolean deleteCommentByID(int comment_id);

}
