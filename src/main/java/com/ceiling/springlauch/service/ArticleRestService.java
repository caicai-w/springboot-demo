package com.ceiling.springlauch.service;

import com.ceiling.springlauch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleRestService {

    public String saveArticle(Article article){
        log.info("savaArticle: {}",article);
        return "测试";
    }

}
