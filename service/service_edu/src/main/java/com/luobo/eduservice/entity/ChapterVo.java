package com.luobo.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {
    private String id;
    private String title;
    private String videoSourceId;
    private List<ChapterVo> children = new ArrayList<>();
}
