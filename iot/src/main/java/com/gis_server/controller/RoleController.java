package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.dto.RoleDto;
import com.gis_server.service.SysPermissionService;
import com.gis_server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public JsonResult getRoutes(){
        return ResultTool.success(sysPermissionService.findPermissionList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public JsonResult getRoles(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
        return ResultTool.success(sysRoleService.getRoleByPage(pageNum, pageSize));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public JsonResult addRole(@RequestBody RoleDto roleDto){
        System.out.println(roleDto);
        System.out.println(roleDto.getPermissionIds());
        sysRoleService.addRole(roleDto);
        return ResultTool.success(roleDto);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public JsonResult updateRole(@PathVariable("id") Integer id,
                                 @RequestBody RoleDto roleDto){
        System.out.println("id=" + id);
        roleDto.setId(id);
        sysRoleService.updateRole(roleDto);
        return ResultTool.success("更改角色成功");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteRole(@PathVariable("id") Integer id){
        sysRoleService.deleteRole(id);
        return ResultTool.success("删除角色成功");
    }


}
