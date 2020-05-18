package com.ceiling.springlauch;

import com.ceiling.springlauch.controller.ArticleRestController;
import com.ceiling.springlauch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
//但凡项目里有依赖注入的东西，都要这个注解
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ArticleRestControllerTest2 {

    //上个是自己new的而不是注入
    @Resource
    private MockMvc mockMvc;

    @Resource
    private ArticleRestService service;

//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
//    }

    //这个测试不需要启动项目，不启动servlet容器
    @Test
    public void saveArticle() throws Exception{
        String article = "{\n" +
                "    \"id\": 1,\n" +
                "    \"author\": \"aaa\",\n" +
                "    \"title\": \"spring boot\",\n" +
                "    \"content\": \"c\",\n" +
                "    \"createTime\": \"2020-07-16 05:23:34\",\n" +
                "    \"readers\":[{\"name\":\"aaa\",\"age\":18},{\"name\":\"bbb \",\"age\":37}]\n" +
                "}";

        MvcResult result = mockMvc.perform(//模拟请求
                MockMvcRequestBuilders.request(HttpMethod.POST,"/rest/article")
                        .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //预期结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("aaa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.readers[0].age").value(18))
                //做点事儿
                .andDo(print())
                //返回controller返回的结果，保存到result
                .andReturn();

        //从result取出来打印
        log.info(result.getResponse().getContentAsString());

    }
}
