package com.ceiling.springlauch.controller;

import com.ceiling.springlauch.model.AjaxResponse;
import com.ceiling.springlauch.model.Article;
import com.ceiling.springlauch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ResponseBody和RequestBody用于接收和返回json数据，支持嵌套
 */


@Slf4j
@RestController
@RequestMapping("/rest")
//ResponseBody是返回json类型的
public class ArticleRestController {

    @Resource
    ArticleRestService service;

    //和下面是同一个意思 @RequestMapping(value = "/article",method = POST,produces = "application/json")
    @PostMapping("/article")
    //RequestParam参数都是一个一个接的，比较麻烦，body是直接一个json发过来能直接转成一个类，类里面的list也可以直接接收
    public AjaxResponse saveArticle(@RequestBody Article article){

        log.info("savaArticle: {}",article);
        return AjaxResponse.success(article);
    }

   // @RequestMapping(value = "/article/{id}",method = DELETE,produces = "application/json")
    @DeleteMapping("/article/{id}")
    //RequestParam是提交上来的参数，PathVariable是路径上的参数
    public AjaxResponse deleteArticle(@PathVariable/*路径变量*/ Long id){

        log.info("deleteArticle：{}",id);
        return AjaxResponse.success(id);
    }

    //@RequestMapping(value = "article/{id}",method = PUT,produces = "application/json")
    @PutMapping("article/{id}")
    public AjaxResponse updateArticle(@PathVariable Long id,@RequestBody Article article){
        article.setId(id);
        log.info("updateArticle:{}",article);
        return AjaxResponse.success(article);
    }

    //@RequestMapping(value = "article/{id}",method = GET,produces = "application/json")
    @GetMapping("article/{id}")
    public AjaxResponse getArticle(@PathVariable Long id){

        Article article = Article.builder().author("me").content("123").creatTime(new Date()).title("aaa").build();
        return AjaxResponse.success(article);
    }
}
