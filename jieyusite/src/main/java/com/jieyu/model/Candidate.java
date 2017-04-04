package com.jieyu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by orangeclk on 7/11/16.
 */
public final class Candidate {
    final private int id;
    final private String description;
    final private String name;

    public static Candidate valueOf(Author author) {
        return new Candidate(author.getId(), author.getDescription(), "");
    }

    public static Candidate valueOf(Location location) {
        return new Candidate(location.getId(), location.getDescription(), "");
    }

    public static Candidate valueOf(Topic topic) {
        return new Candidate(topic.getId(), "", topic.getName());
    }

    private Candidate(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
