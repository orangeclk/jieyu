package com.jieyu.controller;

import com.jieyu.model.Article;
import com.jieyu.model.Location;
import com.jieyu.service.LocationService;
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
 * Created by orangeclk on 7/12/16.
 */
@Controller
@RequestMapping("/location/{id}")
public class LocationController {
    @Autowired
    LocationService service;

    @RequestMapping
    public String location(Model model, @PathVariable int id, Pageable pageable) {
        Location location = service.findOne(id);
        model.addAttribute(location);

        List<Article> articleList = location.getArticles();
        Page<Article> articlePage = new PageImpl<>(articleList, pageable, articleList.size());
        model.addAttribute("page", articlePage);

        return "location";
    }
}
