package com.jieyu.service;

import com.jieyu.model.Article;
import com.jieyu.model.Author;
import com.jieyu.model.Topic;
import com.jieyu.repository.ArticleRepository;
import com.jieyu.repository.AuthorRepository;
import com.jieyu.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
@Service
public class TopicServiceImpl implements TopicService {


    @Autowired
    private TopicRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Topic findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public Topic findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Topic update(int id, int parentId, Stream<Integer> relatedTopicIds) {
        Topic topic = repository.findOne(id);
        if (parentId != -1) {
            topic.setParent(repository.findOne(parentId));
        }
        topic.addRelatedTopics(relatedTopicIds.map(repository::findOne));
        return repository.save(topic);
    }

    @Override
    public Topic update(int id, int parentId, List<Integer> relatedTopicIds) {
        return update(id, parentId, relatedTopicIds.stream());
    }

    @Override
    public Topic saveNew(Topic topic) {
        repository.save(topic);
        topic.createLink();
        return repository.save(topic);
    }

    @Override
    public Stream<Topic> getLatestTopics(int authorId) {
        Author author = authorRepository.findOne(authorId);
        // // TODO: 7/26/16 Find the first 5. Maybe we need criteria or hql
        return author
                .getArticles()
                .stream()
                .map(Article::getTopic)
                .distinct();
    }
}