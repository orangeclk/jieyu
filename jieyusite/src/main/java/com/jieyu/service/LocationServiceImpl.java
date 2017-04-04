package com.jieyu.service;

import com.jieyu.model.Candidate;
import com.jieyu.model.Location;
import com.jieyu.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
@Service
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationRepository repository;

    @Override
    public Location findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public Location findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Stream<Location> findByIds(Stream<Integer> ids) {
        return ids.map(repository::findOne);
    }

    @Override
    public Location saveNew(String name, String parentName) {
        Location parent = repository.findByName(parentName);
        if (parent == null) {
            return null;
        } else {
            Location location = new Location();
            location.setName(name);
            location.setParent(parent);
            repository.save(location);
            location.createLink();
            location.createDescription();
            return repository.save(location);
        }
    }

    @Override
    public Location saveNew(String name) {
        Location location = new Location();
        location.setName(name);
        repository.save(location);
        location.createLink();
        location.createDescription();
        return repository.save(location);
    }
}
