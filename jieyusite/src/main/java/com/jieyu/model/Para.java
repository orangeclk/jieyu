package com.jieyu.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by orangeclk on 6/30/16.
 */
public final class Para {
    final private String title;
    final private List<Integer> authorIds;
    final private List<Integer> locationIds;
    final private String content;
    final private int topicId;
    final private int parentTopicId;
    final private List<Integer> peopleIds;
    final private List<Integer> relatedTopicIds;

    @JsonCreator
    public Para(@JsonProperty("title") String title,
                @JsonProperty("authorIds") List<Integer> authorIds,
                @JsonProperty("locationIds") List<Integer> locationIds,
                @JsonProperty("content") String content,
                @JsonProperty("topicId") int topicId,
                @JsonProperty("parentTopicId") int parentTopicId,
                @JsonProperty("relatedTopicIds") List<Integer> relatedTopicIds,
                @JsonProperty("peopleIds") List<Integer> peopleIds) {

        this.title = title;
        this.authorIds = authorIds;
        this.locationIds = locationIds;
        this.content = content;
        this.topicId = topicId;
        this.parentTopicId = parentTopicId;
        this.peopleIds = peopleIds;
        this.relatedTopicIds = relatedTopicIds;
    }

    public int getParentTopicId() {
        return parentTopicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getAuthorIds() {
        return Collections.unmodifiableList(authorIds);
    }

    public List<Integer> getLocationIds() {
        return Collections.unmodifiableList(locationIds);
    }

    public String getContent() {
        return content;
    }

    public List<Integer> getPeopleIds() {
        return Collections.unmodifiableList(peopleIds);
    }

    public List<Integer> getRelatedTopicIds() {
        return Collections.unmodifiableList(relatedTopicIds);
    }
}
