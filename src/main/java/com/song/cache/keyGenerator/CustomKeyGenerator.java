package com.song.cache.keyGenerator;


import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by Song on 2017/7/4.
 */
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String simpleName = target.getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(simpleName + ":");
        for (Object o :params) {
            sb.append(o.hashCode() + ":");
        }

        return sb.substring(0,sb.length()-1).toString();
    }
}
