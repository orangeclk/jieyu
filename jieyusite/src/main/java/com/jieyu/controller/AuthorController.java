package com.jieyu.controller;

import com.jieyu.model.Article;
import com.jieyu.model.Author;
import com.jieyu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by orangeclk on 7/13/16.
 */
@Controller
@RequestMapping("/author/{id}")
public class AuthorController {
    @Autowired
    AuthorService service;

    @RequestMapping
    public String author(Model model, @PathVariable int id, Pageable pageable) {
        Author author = service.findOne(id);
        model.addAttribute(author);

        List<Article> articleList = author.getArticles();
        Page<Article> articlePage = new PageImpl<Article>(articleList, pageable, articleList.size());
        model.addAttribute("page", articlePage);

        return "author";
    }
}
