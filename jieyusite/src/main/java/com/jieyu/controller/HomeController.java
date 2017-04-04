package com.jieyu.controller;

import com.jieyu.model.Article;
import com.jieyu.repository.ArticleRepository;
import com.jieyu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by orangeclk on 5/24/16.
 */
@Controller
public class HomeController {

    @Autowired
    private ArticleService service;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute(service
                .findTop10ByOrderByDateDesc()
                .collect(Collectors.toList()));
        return "home";
    }
}
