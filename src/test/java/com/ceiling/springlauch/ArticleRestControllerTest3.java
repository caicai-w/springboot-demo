package com.ceiling.springlauch;

import com.ceiling.springlauch.model.Article;
import com.ceiling.springlauch.service.ArticleRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
/**
 * WebMvcTest只测controller，
 * 可以直接WebMvcTest(XXController.class)
 * service是注入不进来的，所以下面用了MockBean
 *
 * springbootTest是加了所有的bean，影响测试速度
 */
@WebMvcTest
public class ArticleRestControllerTest3 {

    @Resource
    private MockMvc mockMvc;

    //伪造
    @MockBean
    private ArticleRestService service;

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


        ObjectMapper objectMapper = new ObjectMapper();
        Article article1 = objectMapper.readValue(article,Article.class);

        /**
         * 打桩
         * 当在ArticleRestController中的saveArticle()，执行service.saveArticle(article)时
         * 不会真正执行那个方法，而是return ok
         */
        when(service.saveArticle(article1)).thenReturn("ok");

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
