package com.jieyu.service;

import com.jieyu.model.People;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
public interface PeopleService {
    People saveNew(People people);
    Stream<People> findByName(String name);
    Stream<People> findByIds(Stream<Integer> ids);
}
