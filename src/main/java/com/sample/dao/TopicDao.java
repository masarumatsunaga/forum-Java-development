package com.sample.dao;

import java.util.List;

import com.sample.topic;

public interface TopicDao {

	public List<topic> selectAll();

	public void insert(topic topic);

	public topic findByPrimarykey(int topicid);

	public void update(topic topic);

}
