package com.luobo.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.article.entity.EduArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.article.entity.vo.ArticleCommitForm;
import com.luobo.article.entity.vo.ArticleSearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luobo
 * @since 2021-08-07
 */
public interface EduArticleService extends IService<EduArticle> {
    List<EduArticle> getAllArticle();

    Map<String, Object> getPageArticle(Page<EduArticle> articlePage, ArticleSearchVo searchVo);

    byte[] saveImg(MultipartFile file);

    byte[] getImg(String imgPath, String fileName);

    void addArticle(ArticleCommitForm commitForm);

    EduArticle getArticleById(String id);

    void updateArticleById(String id, ArticleCommitForm commitForm);

    void deleteArticleById(String id);
}
