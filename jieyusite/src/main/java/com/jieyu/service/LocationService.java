package com.jieyu.service;

import com.jieyu.model.Candidate;
import com.jieyu.model.Location;

import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/10/16.
 */
public interface LocationService {
    Location saveNew(String name, String parentName);
    Location saveNew(String name);
    Location findByName(String name);
    //Candidate findCandidateByName(String name);
    Stream<Location> findByIds(Stream<Integer> ids);
    Location findOne(int id);
}
