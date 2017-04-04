package com.jieyu.repository;

import com.jieyu.model.Article;
import com.jieyu.model.Author;
import com.jieyu.model.Location;
import com.jieyu.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 16-4-11.
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    Stream<Article> findTop10ByOrderByDateDesc();
    Page<Article> findByTopicOrderByDateDesc(Topic topic, Pageable pageable);
}