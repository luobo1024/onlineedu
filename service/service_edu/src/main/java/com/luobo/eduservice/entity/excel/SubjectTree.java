package com.luobo.eduservice.entity.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectTree {
    private String id;
    private String label;
    private List<SubjectTree> children = new ArrayList<>();
}
