package com.jieyu.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 16-4-12.
 */
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_id_gen")
    @SequenceGenerator(name = "topic_id_gen", sequenceName = "topic_id_seq", allocationSize = 1)
    private int id;

    private String name;

    @ManyToOne
    private Topic parent;

    @OneToMany(mappedBy = "parent")
    private Set<Topic> childTopics = new HashSet<>();

    @OneToMany
    private Set<Topic> relatedTopics = new HashSet<>();

    public static Topic valueOf(String name) {
        Topic topic = new Topic();
        topic.setName(name);
        return topic;
    }

    private String link;

    public void createLink() {
        link = "http://localhost:8080/topic/" + id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getParent() {
        return parent;
    }

    public void setParent(Topic parent) {
        this.parent = parent;
    }

    public Set<Topic> getChildTopics() {
        return Collections.unmodifiableSet(childTopics);
    }

    public void setChildTopics(Set<Topic> childTopics) {
        this.childTopics = new HashSet<>(childTopics);
    }

    public Set<Topic> getRelatedTopics() {
        return Collections.unmodifiableSet(relatedTopics);
    }

    public void setRelatedTopics(Set<Topic> relatedTopics) {
        this.relatedTopics = new HashSet<>(relatedTopics);
    }

    public void addRelatedTopics(Stream<Topic> topicStream) {
        relatedTopics.addAll(topicStream.collect(Collectors.toSet()));
    }
}