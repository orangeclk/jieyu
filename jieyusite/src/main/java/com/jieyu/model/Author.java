package com.jieyu.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by orangeclk on 16-4-12.
 */
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_gen")
    @SequenceGenerator(name = "author_id_gen", sequenceName = "author_id_seq", allocationSize = 1)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "authors")
    @OrderBy("date desc")
    private List<Article> articles = new ArrayList<>();

    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(description, author.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public void createLink() {
        link = "http://localhost:8080/author/" + id;
    }

    public List<Article> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    public void setArticles(List<Article> articles) {
        this.articles = new ArrayList<>(articles);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}