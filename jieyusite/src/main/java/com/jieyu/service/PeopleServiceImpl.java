package com.jieyu.service;

import com.jieyu.model.People;
import com.jieyu.repository.PeopleRepository;
import com.sun.xml.internal.ws.resources.StreamingMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
@Service
public class PeopleServiceImpl implements PeopleService {


    @Autowired
    private PeopleRepository repository;

    @Override
    public People saveNew(People people) {
        repository.save(people);
        people.createLink();
        return repository.save(people);
    }

    @Override
    public Stream<People> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Stream<People> findByIds(Stream<Integer> ids) {
        return ids.map(repository::findOne);
    }
}
