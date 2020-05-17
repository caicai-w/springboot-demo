package com.ceiling.springlauch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Article {

    @JsonIgnore//序列化的时候忽略这个字段
    private Long id;
    @JsonProperty("新名字")//也就是前端用"新名字"，后端才能识别到，return回去的也是新名字
    private String author;
    private String title;
    private String content;
    private Date creatTime;
    private List<Reader> readers;
}
