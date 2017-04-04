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
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository repository;

    @Override
    public Article saveNew(Article article) {
        repository.save(article);
        article.createLink();
        return repository.save(article);
    }

    @Override
    public Article findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public Stream<Article> findTop10ByOrderByDateDesc() {
        return repository.findTop10ByOrderByDateDesc();
    }

    @Override
    public Page<Article> findByTopicOrderByDateDesc(Topic topic, Pageable pageable) {
        return repository.findByTopicOrderByDateDesc(topic, pageable);
    }
}
