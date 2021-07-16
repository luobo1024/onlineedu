package com.luobo.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//查询条件封装
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔:1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间")
    private String begin;
    @ApiModelProperty(value = "查询结束时间")
    private String end;
}
