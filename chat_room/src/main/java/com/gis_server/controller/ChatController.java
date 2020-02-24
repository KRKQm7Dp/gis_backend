package com.gis_server.controller;

import com.gis_server.dto.UserDto;
import com.gis_server.pojo.Message;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.FriendService;
import com.gis_server.service.MsgService;
import com.gis_server.service.UserService;
import com.gis_server.utils.MsgUtils;
import org.apache.catalina.mapper.Mapper;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MsgService msgService;

    @Value("${auth-server}")
    private String auth_server;

    @RequestMapping("/chatroom")
    public String chat(@RequestParam(value = "UAA_ACCESS_TOKEN", required = false) String token,
                       HttpServletResponse resp,
                       Model model) throws IOException {
        System.out.println("============ chat =============");
        System.out.println("token = " + token);
        if (token == null && !token.equals("undefined")){
            return "redirect:/login";  // 这种方法重定向不需要 server.servlet.context-path 配置的前缀
        }
        resp.addCookie(new Cookie("UAA_ACCESS_TOKEN", token));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(auth_server + "/oauth/check_token");
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("token", token));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String res = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
        System.out.println(res);
        JSONObject jsonObject = new JSONObject(res);
        if (jsonObject.has("user_name")){
            SysUser user = userService.queryUserByLoginID(jsonObject.getString("user_name"));
            model.addAttribute("user", user);
            System.out.println(user);
            return "chat";
        }else{
            return "redirect:/login";
        }
    }

    /**
     * 此 servlet 用于读取用户历史记录
     * 请求消息格式：
     * req{
     *      from："10000",
     *      to: "10001",
     *      timeStamp:"当前时间",  // 表示获取当前时间之前的 msgNum 条消息
     *      msgNum: 10
     *    }
     */
    @PostMapping("/loadHisMsg")
    public void loadHisMis(@RequestBody Map<String, String> map,
                                       HttpServletResponse response) throws ParseException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        logger.info("用户：" + map.get("from") + " 请求查询 "
                + map.get("timeStamp") + "之前的 "+ map.get("msgNum") +" 条历史消息");
        List<Message> msgs = msgService.queryMsgByPage(map.get("from"),
                map.get("to"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("timeStamp")),
                Integer.parseInt(map.get("msgNum")));
        JSONArray jsonArray = new JSONArray();
        msgs.forEach(msg -> {
            jsonArray.put(MsgUtils.msgToJson(msg));
        });

        PrintWriter writer = response.getWriter();
        writer.println(jsonArray.toString());
        writer.close(); // ajax 返回的时候必须关闭流
    }

    @ResponseBody
    @PostMapping("/userlist")
    public String friendsList(@RequestBody Map<String, String> map) {
        logger.info("获取：" + map.get("loginUserID") + "的好友列表");
        List<UserDto> friends = friendService.queryMyFriends(map.get("loginUserID"));
        JSONArray jsonArray = new JSONArray();
        friends.forEach(f -> {
            JSONObject user_json = new JSONObject();
            user_json.put("uLoginId", f.getuLoginid());
            user_json.put("uHeadPortrait", f.getuHeadportrait());
            user_json.put("uNickName", f.getuNickname());
            user_json.put("fFriendGroupsID", f.getfFriendgroupsid());
            jsonArray.put(user_json);
        });
        return jsonArray.toString();
    }


}
