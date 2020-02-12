package com.gis_server.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getNow() {
        LocalDateTime date = LocalDateTime.now();
        String now = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return now;
    }

    /**
     * 将String字符串转换为 LocalDateTime 格式日期,用于数据库保存
     * @param strDate
     *            表示日期的字符串
     * @param dateFormat
     *            传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return LocalDateTime 类型日期对象（如果转换失败则返回null）
     */
    public static LocalDateTime strToSqlDate(String strDate, String dateFormat) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 将 LocalDateTime 对象转化为String字符串
     * @param time
     *            要格式的 LocalDateTime 对象
     * @param strFormat
     *            输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return 表示日期的字符串
     */
    public static String dateToStr(LocalDateTime time, String strFormat) {
        return time.format(DateTimeFormatter.ofPattern(strFormat));
    }

}
