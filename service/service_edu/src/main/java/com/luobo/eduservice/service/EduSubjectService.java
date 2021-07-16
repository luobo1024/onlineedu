package com.luobo.eduservice.service;

import com.luobo.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.eduservice.entity.excel.SubjectTree;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<SubjectTree> getAllSubject();
}
