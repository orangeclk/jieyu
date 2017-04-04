package com.jieyu.service;

import com.jieyu.model.Article;
import com.jieyu.model.Location;
import com.jieyu.model.Topic;
import com.jieyu.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
public interface ArticleService {
    Article saveNew(Article article);
    Article findOne(int id);
    Stream<Article> findTop10ByOrderByDateDesc();
    Page<Article> findByTopicOrderByDateDesc(Topic topic, Pageable pageable);
}