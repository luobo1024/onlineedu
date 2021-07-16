package com.luobo.eduservice.service;

import com.luobo.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoById(String videoId);
}
