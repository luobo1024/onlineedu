package com.luobo.educms.controller;


import com.luobo.commonutils.Msg;
import com.luobo.educms.entity.CrmBanner;
import com.luobo.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;
    @GetMapping("/getAllBanner")
    public Msg getAllBanner(){
        List<CrmBanner> banners = crmBannerService.selectAllBanner();
        return Msg.success().data("banners",banners);
    }

}

