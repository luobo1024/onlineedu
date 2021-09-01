package com.luobo.article.entity.vo;

import lombok.Data;

//文章条件查询的Vo类
@Data
public class ArticleSearchVo {
    private String title;
    private Integer status;
}
