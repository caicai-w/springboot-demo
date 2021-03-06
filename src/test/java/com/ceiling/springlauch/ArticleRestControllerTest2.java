package com.ceiling.springlauch;

import com.ceiling.springlauch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
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

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * ArticleRestControllerTest不能注入
 * 但凡controller里有service就不能用
 * 这个Test加入了Runwith是可以注入的
 */
@Slf4j
//但凡项目里有依赖注入的东西，都要这个注解
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//找主配置类，结合runwith找所有的bean
@SpringBootTest
public class ArticleRestControllerTest2 {

    //上个是自己new的而不是注入
    @Resource
    private MockMvc mockMvc;

//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
//    }

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
