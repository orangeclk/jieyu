package com.jieyu.model;

import com.jieyu.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.CollectionTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/22/16.
 */
public class PeoplePick {
    final private String name;
    final private List<People> candidates;

    public static PeoplePick newInstance(String name, Stream<People> peopleStream) {
        return new PeoplePick(name, peopleStream);
    }

    private PeoplePick(String name, Stream<People> peopleStream) {
        this.name = name;
        this.candidates = Collections.unmodifiableList(peopleStream.collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public List<People> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }
}
