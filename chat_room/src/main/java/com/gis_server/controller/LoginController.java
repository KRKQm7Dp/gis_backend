package com.gis_server.controller;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * oauth2 对接认证服务流程
 */
@Controller
public class LoginController {

    @Value("${server.servlet.context-path}")
    private String context_path;

    @Value("${auth-server}")
    private String auth_server;

    @GetMapping("/login")
    public void login(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException {
        String webAuthUrl = String.format(auth_server + "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=ROLE_ADMIN",
                "c1",
                "http://localhost:8082/chat/callback");
        System.out.println(webAuthUrl);
        resp.sendRedirect(webAuthUrl);
    }

    @GetMapping("/callback")
    public void callback(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        System.out.println(code);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(auth_server + "/oauth/token");
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", "c1"));
        params.add(new BasicNameValuePair("client_secret", "secret"));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8082/chat/callback"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String res = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
        System.out.println(res);
        JSONObject jsonObject = new JSONObject(res);
        resp.addCookie(new Cookie("UAA_ACCESS_TOKEN", jsonObject.getString("access_token")));
        resp.sendRedirect("/chat/chatroom?UAA_ACCESS_TOKEN=" + jsonObject.getString("access_token"));

    }

}
