package com.song.shiro.filter;

import com.song.common.Constants;
import com.song.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Song on 2017/6/30.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //登录成功后,将用户放入session
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Constants.CURRENT_USER,userService.findByUsername(getUsername(request)));
        return super.onLoginSuccess(token, subject, request, response);
    }

}
