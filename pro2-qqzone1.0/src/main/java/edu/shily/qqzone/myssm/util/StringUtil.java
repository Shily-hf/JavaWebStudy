package edu.shily.qqzone.myssm.util;

/**
 * @author Shily-zhang
 * @Description
 */
public class StringUtil {

    //判断字符串是否为null或""
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
