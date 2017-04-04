package com.jieyu.service;

import com.jieyu.model.Author;
import com.jieyu.model.Candidate;
import com.jieyu.model.Topic;

import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
public interface AuthorService {
    Author findOne(int id);
    Author saveNew(Author author);
    Stream<Author> findByName(String name);
    Stream<Author> findByIds(Stream<Integer> ids);
}
