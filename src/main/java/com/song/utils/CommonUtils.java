package com.song.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/6/30.
 */
public class CommonUtils {

    /**
     * 将字符串转为Long类型的列表
     * @param string
     * @param separator
     * @return
     * @throws Exception
     */
    public static List<Long> parseString2LongCollection(String string,String separator) throws Exception{
        if (StringUtils.isBlank(string)||StringUtils.isBlank(separator)) {
            return null;
        }

        String[] arr = string.split(separator);
        List<Long> list = new ArrayList<>();

        for (String s : arr) {
            list.add(Long.parseLong(s));
        }
        return list;
    }
}
