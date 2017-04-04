package com.jieyu.repository;

import com.jieyu.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 16-4-12.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Stream<Author> findByName(String name);
}