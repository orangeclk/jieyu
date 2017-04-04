package com.jieyu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by orangeclk on 16-4-19.
 */
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_gen")
    @SequenceGenerator(name = "location_id_gen", sequenceName = "location_id_seq", allocationSize = 1)
    private int id;

    @Column(unique = true)
    @NotNull
    private String name;

    @ManyToOne
    private Location parent;

    private String description;

    @ManyToMany(mappedBy = "locations")
    @OrderBy("date desc")
    private List<Article> articles = new ArrayList<>();

    private String link;

    public void createDescription() {
        if (parent == null) {
            description = name;
        } else {
            description = parent.getDescription() + name;
        }
    }

    public void createLink() {
        link = "http://localhost:8080/location/" + id;
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

    public List<Article> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    public void setArticles(List<Article> articles) {
        this.articles = new ArrayList<>(articles);
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

    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}