package com.jieyu.repository;

import com.jieyu.model.Article;
import com.jieyu.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 16-4-12.
 */
public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic findByName(String name);
}