package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.enums.ResultCode;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.iot.IotServerHandler;
import com.gis_server.pojo.Device;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.DeviceService;
import com.gis_server.service.SysUserService;
import com.gis_server.utils.DateUtils;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class DeviceController {

    @Autowired
    private  SysUserService sysUserService;

    @Autowired
    private  DeviceService deviceService;

    @Value("${netty.port}")
    private int port;

    @GetMapping("/allDevices")
    public JsonResult getAllDevice(Principal principal){
        SysUser user = sysUserService.findUserByLoginID(principal.getName());
        return ResultTool.success(deviceService.selectAllDevices(user.getuId()));
    }

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
        res.put("device-owner-id", user.getuId().toString());
        res.put("device-id", String.valueOf(device.getId()));
        res.put("netty-server-host", InetAddress.getLocalHost().getHostAddress());
        res.put("netty-server-port", String.valueOf(port));


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

    @GetMapping("/send")
    public JsonResult send(@RequestBody Map<String, String> msg){
        System.out.println("发送消息: " + msg.get("toDeviceId"));
        System.out.println("发送消息: " + msg.get("data"));
        Map channelMap = IotServerHandler.channelMap;
        Iterator<Map.Entry<Integer, Channel>> iterator = channelMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Channel> entry = iterator.next();
            if (entry.getKey() == Integer.valueOf(msg.get("toDeviceId"))) {
                entry.getValue().writeAndFlush(msg.get("data"));
            }
        }
        return ResultTool.success("ok");
    }

    @GetMapping("/configJsonToFile")
    public ResponseEntity<byte[]> configJsonToFile(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestParam("data") String data) throws IOException {

        String fileName = DateUtils.getNow("yyyyMMddHHmmss") + "_config.json";

        // 4.设置格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", encodeChineseDownloadFileName(request, fileName));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 5.返回下载
        return new ResponseEntity<byte[]>(data.getBytes(),
                headers, HttpStatus.OK);
    }

    private String encodeChineseDownloadFileName(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {
        String resultFileName = "";
        String agent = request.getHeader("User-Agent").toUpperCase();
        if(null == agent){
            resultFileName = fileName;
        }else{
            if(agent.indexOf("FIREFOX") != -1 || agent.indexOf("CHROME") != -1){  //firefox, chrome
                resultFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }else{  //ie7+
                resultFileName =  URLEncoder.encode(fileName, "UTF-8");
                resultFileName = StringUtils.replace(resultFileName, "+", "%20");  //替换空格
            }
        }
        return resultFileName;
    }

}
