package com.song.listener;


import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Song on 2017/7/4.
 */
public class LocalHttpSessionContext {
    public static Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }
    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return (HttpSession) sessionMap.get(session_id);
    }
}
