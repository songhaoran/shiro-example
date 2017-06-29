package com.song.shiro.filter;

import com.song.common.Constants;
import com.song.domain.SysUser;
import com.song.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Song on 2017/6/28.
 * 将登录用户对象放到requst域中,需要限制性user过滤器
 */
public class UserFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser user = userService.findByUsername(userName);
        request.setAttribute(Constants.CURRENT_USER, user);
        return super.onPreHandle(request, response, mappedValue);
    }
}
