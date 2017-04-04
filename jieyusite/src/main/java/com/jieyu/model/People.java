package com.jieyu.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by orangeclk on 16-4-12.
 */
@Entity
public class People {

    //考虑重名问题，修改candidate模式，把编辑界面的文本框放在最左边；这个people放在作者和地点之下，放在中间一列；话题最右一列。

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_id_gen")
    @SequenceGenerator(name = "people_id_gen", sequenceName = "people_id_seq", allocationSize = 1)
    private int id;

    @NotNull
    private String name;

    private String description;

    private String link;

    @ManyToMany(mappedBy = "peoples")
    private Set<Article> articles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(name, people.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void createLink() {
        link = "http://localhost:8080/people/" + id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public People() {
    }

    public People(String name) {
        this.name = name;
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

    public Set<Article> getArticles() {
        return Collections.unmodifiableSet(articles);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = new HashSet<>(articles);
    }
}