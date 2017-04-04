package com.jieyu.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 7/21/16.
 */
public class AuthorPick {
    final private String name;
    final private List<Author> candidates;

    public static AuthorPick newInstance(String name, Stream<Author> authorStream) {
        return new AuthorPick(name, authorStream);
    }

    private AuthorPick(String name, Stream<Author> authorStream) {
        this.name = name;
        this.candidates = Collections.unmodifiableList(authorStream.collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public List<Author> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }
}
