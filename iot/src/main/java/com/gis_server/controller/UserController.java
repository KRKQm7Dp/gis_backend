package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.entity.Pager;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@RestController
//public class UserController {
//
//    /**
//     * 资源服务器提供的受保护接口
//     * @param principal
//     * @return
//     */
//    @RequestMapping("/user")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public Principal user(Principal principal) {
//        System.out.println(principal);
//        return principal;
//    }
//
//}


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/user/info")
    public JsonResult getUserInfo(Principal principal){
        return ResultTool.success(principal);
    }

//    @RequestMapping(value = "getAllUser", method = RequestMethod.GET)
//    public JsonResult getAllUser(){
//        List<SysUser> users = sysUserService.getAllUser();
//        users.forEach(item -> {
//            item.setuPassword("");
//        });
//        return ResultTool.success(users);
//    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public JsonResult getUserByPage(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize){
        return ResultTool.success(sysUserService.getUserByPage(pageNum, pageSize));
    }

//    @GetMapping("/getUser")
//    public JsonResult getUserById(@RequestParam("username") String uLoginid){
//        SysUser user = sysUserService.findUserByLoginID(uLoginid);
//        user.setuPassword("");
//        return ResultTool.success(user);
//    }


    @PutMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public JsonResult modifyUser(@PathVariable Integer id,
                                 @RequestBody SysUser user){
        System.out.println("-----------modifyUser--------------");
        System.out.println(user);
        user.setuId(id);
        sysUserService.modifyUser(user);
        return ResultTool.success("修改用户成功");
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public JsonResult addUser(@RequestBody SysUser user){
        sysUserService.addUser(user);
        return ResultTool.success("添加用户成功");
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public JsonResult deleteUser(@PathVariable Integer id){
        sysUserService.deleteUser(id);
        return ResultTool.success("删除用户成功");
    }


}
