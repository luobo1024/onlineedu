package com.luobo.eduservice.service.impl;

import com.luobo.eduservice.entity.EduVideo;
import com.luobo.eduservice.mapper.EduVideoMapper;
import com.luobo.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void removeVideoById(String videoId) {
        baseMapper.deleteById(videoId);
    }
}
