package com.bins.binbatis.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leo-bin
 * @date 2020/5/9 18:23
 * @apiNote sql字段解析工具包
 */
public class SqlParserUtil {


    private static final String openToken = "#{";
    private static final String closeToken = "}";


    /**
     * sql解析,用来将sql中的#{userId}等替换为?
     */
    public static Map<String, String> parse(String text) {
        Map<String, String> result = new HashMap<>(16);
        if (text == null || text.isEmpty()) {
            return null;
        }
        // search open token
        int start = text.indexOf(openToken);
        if (start == -1) {
            return null;
        }
        char[] src = text.toCharArray();
        int offset = 0;
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        while (start > -1) {
            // found open token. let's search close token.
            if (expression == null) {
                expression = new StringBuilder();
            } else {
                expression.setLength(0);
            }
            builder.append(src, offset, start - offset);
            offset = start + openToken.length();
            int end = text.indexOf(closeToken, offset);
            while (end > -1) {
                expression.append(src, offset, end - offset);
                offset = end + closeToken.length();
                result.put(expression.toString(), expression.toString());
                break;
            }
            if (end == -1) {
                // close token was not found.
                builder.append(src, start, src.length - start);
                offset = src.length;
            } else {
                //设置为占位符？
                builder.append("?");
                offset = end + closeToken.length();
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        result.put("sql", builder.toString());
        return result;
    }


/*    public static void main(String[] args) {
        String text2 = "insert into user_info(username, password) VALUES (#{userName},#{passWord})";
        Map<String, String> result = parse(text2);
        if (result != null && result.size() > 0) {
            String sql = result.get("sql");
            System.out.println("sql=" + sql);
            UserInfo userInfo = new UserInfo();
            userInfo.setId(1);
            userInfo.setUserName("李斌");
            userInfo.setPassWord("115118");
            Field[] fields = userInfo.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (result.containsKey(field.getName())) {
                        System.out.println(field.getName() + "," + field.get(userInfo));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}
