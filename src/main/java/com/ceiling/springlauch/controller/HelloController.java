package com.ceiling.springlauch.controller;


import com.ceiling.springlauch.model.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String showHello(){
        return "hello!";
    }

    @RequestMapping("/art")
    public String getName(){
        Article article = Article.builder().id(1L).name("Pink").build();
        return article.getName();
    }
}
