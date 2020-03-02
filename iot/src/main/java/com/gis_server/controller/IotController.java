package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.enums.ResultCode;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.iot.IotServerHandler;
import com.gis_server.pojo.Device;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.DeviceService;
import com.gis_server.service.SysUserService;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class IotController {

    @Autowired
    private  SysUserService sysUserService;

    @Autowired
    private  DeviceService deviceService;

    @Value("${netty.port}")
    private int port;

    @GetMapping("/devices")
    public JsonResult getDeviceByPage(Principal principal,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam(value = "search", required = false) String search){
        SysUser user = sysUserService.findUserByLoginID(principal.getName());
        return ResultTool.success(deviceService.selectByPage(user.getuId(), pageNum, pageSize, search));
    }

    @PostMapping("/device")
    public JsonResult addDevice(Principal principal,
                                @RequestBody Device device,
                                HttpServletResponse response) throws UnknownHostException {
        response.setCharacterEncoding("UTF-8");

        SysUser user =sysUserService.findUserById(device.getUserId());
        if(!user.getuLoginid().equals(principal.getName())){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }

        deviceService.addNewDevice(device);

        Map<String, String> res = new HashMap<>();
        res.put("userId", user.getuId().toString());
        res.put("deviceId", String.valueOf(device.getId()));
        res.put("host", InetAddress.getLocalHost().getHostAddress());
        res.put("port", String.valueOf(port));


        return ResultTool.success(res);
    }

    @DeleteMapping("/device/{id}")
    public JsonResult delDevice(@PathVariable("id") Integer id){
        deviceService.deleteDevice(id);  // 移除设备时会将其所采集到的温湿度信息清空
        return ResultTool.success("移除设备成功");
    }

    @PutMapping("/device")
    public JsonResult updateDevice(@RequestBody Device device){
        deviceService.updateDevice(device);
        return ResultTool.success("修改设备成功");
    }

    @GetMapping("/send/{msg}")
    public JsonResult send(@PathVariable String msg){
        System.out.println("发送消息: " + msg);
        ChannelGroup channels = IotServerHandler.channels;
        channels.forEach(channel -> {
            channel.writeAndFlush(msg);
        });
        return ResultTool.success("ok");
    }

}
