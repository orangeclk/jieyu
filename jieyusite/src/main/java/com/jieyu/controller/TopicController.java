package com.jieyu.controller;

import com.jieyu.model.Topic;
import com.jieyu.repository.TopicRepository;
import com.jieyu.service.ArticleService;
import com.jieyu.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by orangeclk on 7/9/16.
 */
@Controller
@RequestMapping("/topic/{id}")
public class TopicController {
    @Autowired
    private TopicService topicService; //// TODO: 8/5/16 Change to Inject, Erase the warning.

    @Autowired
    private ArticleService articleService;

    @RequestMapping
    public String topic(Model model, @PathVariable int id, Pageable pageable) {
        Topic topic = topicService.findOne(id);
        model.addAttribute(topic);
        model.addAttribute("page", articleService.findByTopicOrderByDateDesc(topic, pageable));
        return "topic";
    }
}