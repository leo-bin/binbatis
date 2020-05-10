package com.bins.binbatis.util;

import org.apache.commons.text.CaseUtils;

/**
 * @author leo-bin
 * @date 2020/5/9 19:11
 * @apiNote
 */
public class StringUtil {

    private final static String UNDERLINE = "_";

    /***
     * 下划线命名转为驼峰命名
     *
     * @param para 下划线命名的字符串
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split(UNDERLINE);
        for (String s : a) {
            if (!para.contains(UNDERLINE)) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /***
     * 驼峰命名转为下划线命名
     *
     * @param para 驼峰命名的字符串
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        //定位
        int temp = 0;
        if (!para.contains(UNDERLINE)) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, UNDERLINE);
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }


    public static void main(String[] args) {
        String str1 = "id";
        System.out.println(CaseUtils.toCamelCase(underlineToHump(str1), false));
        String str2 = "username";
        System.out.println(CaseUtils.toCamelCase(underlineToHump(str2), false));
        String str3 = "reg_date";
        System.out.println(CaseUtils.toCamelCase(underlineToHump(str3), false));
        System.out.println(humpToUnderline(str1));
        System.out.println(humpToUnderline(str2));
        System.out.println(humpToUnderline(str3));
    }
}
