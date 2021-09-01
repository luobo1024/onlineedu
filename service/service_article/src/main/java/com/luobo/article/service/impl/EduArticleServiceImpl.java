package com.luobo.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.article.entity.EduArticle;
import com.luobo.article.entity.vo.ArticleCommitForm;
import com.luobo.article.entity.vo.ArticleSearchVo;
import com.luobo.article.mapper.EduArticleMapper;
import com.luobo.article.service.EduArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-08-07
 */
@Service
public class EduArticleServiceImpl extends ServiceImpl<EduArticleMapper, EduArticle> implements EduArticleService {
    @Override
    //得到所有文章
    public List<EduArticle> getAllArticle() {
        List<EduArticle> eduArticles = baseMapper.selectList(null);
        return eduArticles;
    }

    @Override
    //分页条件查询
    public Map<String, Object> getPageArticle(Page<EduArticle> articlePage, ArticleSearchVo searchVo) {
        //根据serchVo的title和status查询
        QueryWrapper<EduArticle> wrapper = new QueryWrapper<>();
        if(searchVo != null) {
            if (searchVo.getTitle() != null) {
                wrapper.like("title", searchVo.getTitle());
            }
            if (searchVo.getStatus() != null) {
                wrapper.eq("status", searchVo.getStatus());
            }
        }
        wrapper.orderByDesc("gmt_create");
        //条件筛选后,返回分页结果集
        baseMapper.selectPage(articlePage,wrapper);
        //得到文章列表
        List<EduArticle> records = articlePage.getRecords();
        //得到分页数量
        long size = articlePage.getSize();
        //得到当前页
        long current = articlePage.getCurrent();
        //是否有下一页
        boolean hasNext = articlePage.hasNext();
        //是否有上一页
        boolean hasPrevious = articlePage.hasPrevious();
        //得到全部数据数目
        long total = articlePage.getTotal();
        //map接收数据
        Map<String, Object> map = new HashMap<>();
        map.put("records",records);
        map.put("size",size);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("total",total);
        return map;
    }

    @Override
    //上传图片到服务器本地
    public byte[] saveImg(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] cbyte = new byte[is.available()];
            is.read(cbyte);
            return cbyte;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public byte[] getImg(String imgPath, String fileName) {
        FileInputStream fos = null;
        try {
            imgPath = "D://" + imgPath;//需要修改
            File file = new File(imgPath +File.separator + fileName);
            fos = new FileInputStream(file);
            byte[] cbyte = new byte[fos.available()];
            fos.read(cbyte);
            return cbyte;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    //添加文章到数据库中
    public void addArticle(ArticleCommitForm commitForm) {
        EduArticle article = new EduArticle();
        if(commitForm.getTitle() != null){
            article.setTitle(commitForm.getTitle());
        }
        if(commitForm.getStatus() != null){
            article.setStatus(commitForm.getStatus());
        }
        if(commitForm.getImg() != null){
            article.setCover(commitForm.getImg());
        }
        if(commitForm.getContent() != null){
            article.setContent(commitForm.getContent());
        }
        if(commitForm.getSort() != null){
            article.setSort(commitForm.getSort());
        }
        baseMapper.insert(article);
    }

    @Override
    public EduArticle getArticleById(String id) {
        EduArticle article = baseMapper.selectById(id);
        return article;
    }

    @Override
    public void updateArticleById(String id, ArticleCommitForm commitForm) {
        EduArticle article = new EduArticle();
        article.setId(id);
        if(commitForm.getTitle() != null){
            article.setTitle(commitForm.getTitle());
        }
        if(commitForm.getStatus() != null){
            article.setStatus(commitForm.getStatus());
        }
        if(commitForm.getImg() != null){
            article.setCover(commitForm.getImg());
        }
        if(commitForm.getContent() != null){
            article.setContent(commitForm.getContent());
        }
        if(commitForm.getSort() != null){
            article.setSort(commitForm.getSort());
        }
        baseMapper.updateById(article);
    }
    @Override
    public void deleteArticleById(String id) {
        baseMapper.deleteById(id);
    }

}
