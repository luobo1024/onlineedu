package com.luobo.aclservice.controller;


import com.luobo.aclservice.entity.Permission;
import com.luobo.aclservice.service.PermissionService;
import com.luobo.commonutils.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Msg indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return Msg.sucess().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Msg remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return Msg.sucess();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Msg doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return Msg.sucess();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Msg toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Msg.sucess().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Msg save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Msg.sucess();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Msg updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Msg.sucess();
    }

}

