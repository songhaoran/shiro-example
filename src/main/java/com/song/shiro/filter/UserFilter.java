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
 * 本过滤器的功能是访问指定路径时,获取用户的身份(前提是用户已成功登录),然后根据身份获取用户对象,将对象放到requst域中.本过滤器需设置到过滤器拦截链的user过滤器后面
 * 注:该方式并不好,因为每次请求指定的拦截路径,都会查一次数据库,因此最好是重写表单登录验证过滤器,当登录成功时,将用户对象放到session中
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
