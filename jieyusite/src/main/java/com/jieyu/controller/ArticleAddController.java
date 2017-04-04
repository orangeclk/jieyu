package com.jieyu.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jieyu.model.*;
import com.jieyu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by orangeclk on 16-4-8.
 */
@RestController
public class ArticleAddController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PeopleService peopleService;

    /**
     * Return the picks of author names.
     * @param authorNames
     * @return
     */
    @RequestMapping("/authors-analyze")
    public List<AuthorPick> authorsAnalyze(@RequestBody List<String> authorNames) {
        return authorNames
                .stream()
                .map(authorName -> AuthorPick.newInstance(authorName, authorService.findByName(authorName)))
                .collect(Collectors.toList());
    }

    /**
     * Return the new candidate.
     * Save the new Author.
     * @param newAuthor
     * @return
     */
    @RequestMapping("/add-new-author")
    public Author addNewAuthor(@RequestBody Author newAuthor) {
        return authorService.saveNew(newAuthor);
    }

    /**
     * Return the picks of location names.
     * @param locationNames
     * @return
     */
    @RequestMapping("/locations-analyze")
    public List<Location> locationsAnalyze(@RequestBody List<String> locationNames) {
        return locationNames
                .stream()
                .map(locationService::findByName)
                .collect(Collectors.toList());
    }


    @RequestMapping("/add-new-location")
    public Location addNewLocation(@RequestBody NewLocationInfo newLocation) {
        String name = newLocation.getName();
        String parentName = newLocation.getParentName();

        if (parentName.equals("")) {
            return locationService.saveNew(name);
        } else {
            return locationService.saveNew(name, parentName);
        }
    }

    @RequestMapping("/peoples-analyze")
    public List<PeoplePick> peoplesAnalyze(@RequestBody List<String> peopleNames) {
        return peopleNames
                .stream()
                .map(peopleName -> PeoplePick.newInstance(peopleName, peopleService.findByName(peopleName)))
                .collect(Collectors.toList());
    }

    @RequestMapping("/add-new-people")
    public People addNewPeople(@RequestBody People newPeople) {
        return peopleService.saveNew(newPeople);
    }

    /**
     * Return the topics information of the given authors id.
     * @param authorIds
     * @return
     */
    @RequestMapping("/topic-analyze")
    public List<Topic> topicAnalyze(@RequestBody List<Integer> authorIds) {
        return authorIds
                .stream()
                .filter(authorId -> authorId > -1)
                .flatMap(topicService::getLatestTopics)
                .collect(Collectors.toList());
    }

    @RequestMapping("/topic-find")
    public Topic topicFind(@RequestBody String query) {
        Pattern pattern = Pattern.compile("(.*/)(\\d+)(/?\")");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return topicService.findOne(Integer.parseInt(matcher.group(2)));
        } else {
            query = query.substring(1, query.length() - 1);
            return topicService.findByName(query);
        }
    }

    /**
     * Return the new topic id.
     * Save the new topic.
     * @param topicName
     * @return
     */
    @RequestMapping("/add-new-topic")
    public int addNewTopic(@RequestBody String topicName) {
        topicName = topicName.substring(1, topicName.length() - 1);
        return topicService.saveNew(Topic.valueOf(topicName)).getId();
    }


    /**
     * Submit the article given the parameters.
     * @param para
     */
    @RequestMapping("/submit-article")
    public void submitArticle(@RequestBody Para para) {
        Article newArticle = new Article();

        newArticle.setTitle(para.getTitle());

        newArticle.setAuthors(authorService
                .findByIds(para
                        .getAuthorIds()
                        .stream())
                .collect(Collectors.toSet()));

        newArticle.setLocations(locationService
                .findByIds(para
                        .getLocationIds()
                        .stream())
                .collect(Collectors.toSet()));

        newArticle.setContent(para.getContent());

        newArticle.setTopic(topicService.update(
                para.getTopicId(),
                para.getParentTopicId(),
                para.getRelatedTopicIds()));

        newArticle.setPeoples(peopleService
                .findByIds(para
                        .getPeopleIds()
                        .stream())
                .collect(Collectors.toSet()));

        articleService.saveNew(newArticle);
    }
}