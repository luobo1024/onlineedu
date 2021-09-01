package com.luobo.article.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.article.entity.EduArticle;
import com.luobo.article.entity.vo.ArticleCommitForm;
import com.luobo.article.entity.vo.ArticleSearchVo;
import com.luobo.article.service.EduArticleService;
import com.luobo.commonutils.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/article/edu-article")
@CrossOrigin
public class EduArticleController {
    @Autowired
    private EduArticleService articleService;
    @ApiOperation(value = "得到所有文章")
    @GetMapping("/getAllArticle")
    public Msg getAllArticle(){
        List<EduArticle> articleList =  articleService.getAllArticle();
        return Msg.success().data("articleList", articleList);
    }
    @ApiOperation(value = "分页并且条件查询得到文章")
    @PostMapping("/getPageArticle/{current}/{limit}")
    public Msg getPageArticle(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) ArticleSearchVo searchVo){
        Page<EduArticle> articlePage = new Page<>(current, limit);
        Map<String, Object> map =  articleService.getPageArticle(articlePage, searchVo);
        return Msg.success().data(map);
    }
    @ApiOperation(value = "上传封面")
    @PostMapping("/saveImg")
    public Msg saveImg(@RequestParam("file") MultipartFile file){
        byte[] imgStream = articleService.saveImg(file);
        return Msg.success().data("imgSteam", imgStream);
    }
    @ApiOperation(value = "获取封面")
    @GetMapping("/getImg/{imgPath}/{fileName}")
    public Msg getImg(@PathVariable String imgPath,@PathVariable String fileName){
        byte[] img = articleService.getImg(imgPath, fileName);
        return Msg.success().data("img", img);
    }
    @ApiOperation(value = "获取发表文章表单,写入数据库")
    @PostMapping("/getArticleForm")
    public Msg getArticleForm(@RequestBody ArticleCommitForm commitForm){
        articleService.addArticle(commitForm);
        return Msg.success();
    }
    @ApiOperation(value = "获取编辑文章的数据")
    @GetMapping("/getEditArticle/{id}")
    public Msg getEditArticle(@PathVariable String id){
        EduArticle article =  articleService.getArticleById(id);
        return Msg.success().data("article", article);
    }
    @ApiOperation(value = "修改文章内容")
    @PostMapping("/updateArticle/{id}")
    public Msg updateArticle(@PathVariable String id, @RequestBody ArticleCommitForm commitForm){
        articleService.updateArticleById(id, commitForm);
        return Msg.success();
    }
    @ApiOperation(value = "删除文章")
    @DeleteMapping("/deleteArticle/{id}")
    public Msg delteArticle(@PathVariable String id){
        articleService.deleteArticleById(id);
        return Msg.success();
    }
}

