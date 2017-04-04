package com.jieyu.service;

import com.jieyu.model.*;
import com.jieyu.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public Stream<Author> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Author findOne(int id) {
        return repository.findOne(id);
    }


    @Override
    public Stream<Author> findByIds(Stream<Integer> ids) {
        return ids.map(repository::findOne);
    }

    @Override
    public Author saveNew(Author author) {
        repository.save(author);
        author.createLink();
        return repository.save(author);
    }
}
