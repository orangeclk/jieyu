package com.jieyu.service;

import com.jieyu.model.Article;
import com.jieyu.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
public interface TopicService {
    Topic saveNew(Topic topic);
    Topic findOne(int id);
    Topic update(int id, int parentId, Stream<Integer> relatedTopicIds);
    Topic update(int id, int parentId, List<Integer> relatedTopicIds);
    Topic findByName(String name);
    Stream<Topic> getLatestTopics(int authorId);
}
