package com.ceiling.springlauch;

import com.ceiling.springlauch.controller.ArticleRestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
public class ArticleRestControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
    }

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

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.POST,"/rest/article")
                        .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("aaa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.readers[0].age").value(18))
                .andDo(print())
                .andReturn();

        log.info(result.getResponse().getContentAsString());

    }
}
