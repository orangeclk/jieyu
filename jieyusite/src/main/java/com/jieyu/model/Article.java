package com.jieyu.model;

import com.jieyu.repository.AuthorRepository;
import com.jieyu.repository.TopicRepository;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by orangeclk on 16-4-11.
 */
@Entity
@Component
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_gen")
    @SequenceGenerator(name = "article_id_gen", sequenceName = "article_id_seq", allocationSize = 1)
    private int id;

    @NotNull
    private String title;

    @ManyToMany
    @NotNull
    private Set<Author> authors = new HashSet<>(); //// TODO: 8/5/16 Change to two ManytoOnes.


    @ManyToMany
    @NotNull
    private Set<Location> locations = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date date;

    @NotNull
    private String content;

    @ManyToOne
    @NotNull
    private Topic topic;

    @ManyToMany
    @NotNull
    private Set<People> peoples = new HashSet<>();

    private String link;

    public void createLink() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        link = "http://localhost:8080/article/" + simpleDateFormat.format(calendar.getTime()) + "/" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) &&
                Objects.equals(authors, article.authors) &&
                Objects.equals(date, article.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, date);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = new HashSet<>(authors);
    }

    public Set<Location> getLocations() {
        return Collections.unmodifiableSet(locations);
    }

    public void setLocations(Set<Location> locations) {
        this.locations = new HashSet<>(locations);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Set<People> getPeoples() {
        return Collections.unmodifiableSet(peoples);
    }

    public void setPeoples(Set<People> peoples) {
        this.peoples = new HashSet<>(peoples);
    }

}