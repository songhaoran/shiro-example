package com.song.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Song on 2017/7/4.
 */
public class LocalHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LocalHttpSessionContext.AddSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LocalHttpSessionContext.DelSession(se.getSession());
    }
}
