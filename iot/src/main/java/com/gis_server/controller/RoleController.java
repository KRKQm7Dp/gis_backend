package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.pojo.SysRole;
import com.gis_server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public JsonResult getRoles(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
        return ResultTool.success(sysRoleService.getRoleByPage(pageNum, pageSize));
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public JsonResult addRole(@RequestBody SysRole sysRole){
        sysRoleService.addRole(sysRole);
        return ResultTool.success("添加角色成功");

    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public JsonResult updateRole(@PathVariable("id") Integer id,
                                 @RequestBody SysRole sysRole){
        System.out.println("id=" + id);
        sysRole.setId(id);
        sysRoleService.updateRole(sysRole);
        return ResultTool.success("更改角色成功");
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteRole(@PathVariable("id") Integer id){
        sysRoleService.deleteRole(id);
        return ResultTool.success("删除角色成功");
    }


}
