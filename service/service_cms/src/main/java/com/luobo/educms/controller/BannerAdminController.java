package com.luobo.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.Msg;
import com.luobo.educms.entity.CrmBanner;
import com.luobo.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;
    @GetMapping("/pageBanner/{current}/{limit}")
    public Msg pageTeacher(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                           @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit){
        //创建page对象
        Page<CrmBanner> crmPage = new Page<>(current,limit);
        //调用方法实现分页,底层封装,把分页数据封装到teacherPage中
        crmBannerService.page(crmPage,null);
        //总记录数
        long total = crmPage.getTotal();
        //返回每页数据的List集合
        List<CrmBanner> records = crmPage.getRecords();
        return Msg.sucess().data("total",total).data("rows",records);
    }
    @PostMapping("/addBanner")
    public Msg addBanner(@RequestBody CrmBanner crmBanner)
    {
        crmBannerService.save(crmBanner);
        return Msg.sucess();
    }
    @PostMapping("/updateBanner")
    public Msg updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return Msg.sucess();
    }
    @DeleteMapping("/removeBanner/{bannerId}")
    public Msg removeBanner(@PathVariable String bannerId){
        crmBannerService.removeById(bannerId);
        return Msg.sucess();

    }
}

