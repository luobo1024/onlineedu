package com.luobo.article.entity.vo;

import lombok.Data;

@Data
public class ArticleCommitForm {
    private String title;
    private String content;
    private String img;
    private Integer status;
    private Integer sort;
}
