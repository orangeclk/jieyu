package com.jieyu.repository;

import com.jieyu.model.People;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 5/19/16.
 */
public interface PeopleRepository extends CrudRepository<People, Integer> {
    Stream<People> findByName(String name);
    int countByName(String name);
}