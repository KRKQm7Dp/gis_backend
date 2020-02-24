package com.gis;

import com.gis_server.ChatRoomSpringbootApplication;
import com.gis_server.mapper.MessageMapper;
import com.gis_server.service.impl.RedisService;
import com.gis_server.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootTest(classes = ChatRoomSpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatRoomSpringbootTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisService redisService;

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void test1(){
        System.out.println(redisService.removeOffLineMsg("admin"));
    }

    @Test
    public void dateUtilsTest(){
        System.out.println(DateUtils.strToSqlDate("2020-12-22 21:50:14", "yyyy-MM-dd HH:mm:ss").toString());
    }

    @Test
    public void hismsgTest(){
        messageMapper.queryMsgByPage("admin","chatRoom", Date.from(DateUtils.strToSqlDate("2020-12-22 21:50:14", "yyyy-MM-dd HH:mm:ss").toInstant(ZoneOffset.UTC)), 10);
    }

    @Test
    public void pathTest() throws FileNotFoundException {
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
    }

    @Value("${server.servlet.context-path}")
    private String context_path;

    @Test
    public void propertiesTest(){
        System.out.println(context_path);
    }

    @Test
    public void dateTest() throws ParseException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("Mon Feb 24 17:41:14 CST 2020"));
    }

}
