package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.XinQingComment;

public interface XinQingCommentDao {
	int insertComment(XinQingComment comment);// ����id

	List<XinQingComment> getCommentByXinQingID(int xinqing_id);

	boolean deleteCommentByID(int comment_id);
}
