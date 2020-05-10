package com.bins.binbatis.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yami
 * @apiNote CD工具包
 */
public class CDUtil {

    /**
     * 将正则表达式在编译期间提前准备好，加快匹配速度
     */
    private static Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

    /**
     * @param args 参数数组
     * @return String
     */
    public static String KeyConfusion(String... args) {
        if (args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s).append("_");
        }
        return sb.toString();
    }

    /**
     * 判断list对象是否为空（包括空对象和空值对象）
     *
     * @param list 判断对象
     * @return Boolean 是否为空
     */
    public static Boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断map对象是否为空（包括空对象和空值对象）
     *
     * @param map 判断对象
     * @return Boolean 是否为空
     */
    public static Boolean isEmptyMap(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * @param mobiles
     * @return boolean
     */
    public static boolean isMobile(String mobiles) {
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
