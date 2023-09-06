package com.sample.dao;

import java.util.List;

import com.sample.comment;

public interface CommentDao {

	public List<comment> selectAll();

	public void insert(comment comment);

	public void delete(int id);

	//public comment findPrimarykey(int id);

	public List<comment> findSelect(int topicid);

}
