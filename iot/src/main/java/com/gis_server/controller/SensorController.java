package com.gis_server.controller;

import com.gis_server.common.entity.JsonResult;
import com.gis_server.common.utils.ResultTool;
import com.gis_server.service.TempHumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SensorController {

    @Autowired
    TempHumService tempHumService;

    /**
     * 返回数据是按时间倒序的，需要在前端反转一下
     * @param deviceId
     * @param size
     * @return
     */
    @GetMapping("/tempAndHum")
    public JsonResult getTempAndHumList(@RequestParam("deviceId") Integer deviceId,
                                        @RequestParam("size") Integer size) {
        return ResultTool.success(tempHumService.getTempHumListByDeviceId(deviceId, size));
    }

    @GetMapping("nowTempAndHum")
    public JsonResult getNowTempAndHum(@RequestParam("deviceId") Integer deviceId){
        return ResultTool.success(tempHumService.getLastTempHumByDeviceId(deviceId));
    }
}
