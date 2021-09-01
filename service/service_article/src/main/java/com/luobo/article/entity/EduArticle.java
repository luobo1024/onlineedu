package com.luobo.article.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author luobo
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BlogArticle对象", description="文章")
public class EduArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章封面")
    private String cover;

    @ApiModelProperty(value = "评论数")
    private Integer comment;

    @ApiModelProperty(value = "浏览数")
    private Integer view;

    @ApiModelProperty(value = "排序")
    private Integer sort;


    @ApiModelProperty(value = "编辑中(0), 发布(1)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT, value = "gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "gmt_modified")
    private Date gmtModified;


}
