package com.jieyu.controller;

import com.jieyu.model.Article;
import com.jieyu.repository.ArticleRepository;
import com.jieyu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.List;

/**
 * Created by orangeclk on 6/18/16.
 */
@Controller
@RequestMapping("/article/{date}/{id}")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @RequestMapping
    public String article(Model model, @PathVariable int id) {
        model.addAttribute(service.findOne(id));
        return "article";
    }
}