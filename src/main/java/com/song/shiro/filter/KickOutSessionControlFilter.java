package com.song.shiro.filter;

import com.song.common.Constants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Song on 2017/7/4.
 * 拦截所有需要登陆的url
 * 用来检测session中是否包含该session已被踢出的标记,如果包含该标记,那么将该请求重定向到登陆页面,并提示已被强制退出
 */
public class KickOutSessionControlFilter extends AccessControlFilter {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 被踢出登陆后,重定向的路径
     */
    private String redirectUrl;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //未登陆,未记住我登录,放行
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }

        //判断是否被踢出
        HttpServletRequest req = (HttpServletRequest) request;
        Object attr = req.getSession().getAttribute(Constants.KICK_OUT_SESSION);
        if (attr != null) {
            subject.logout();
            saveRequest(request);//会把session删掉
            HashMap<Object, Object> params = new HashMap<>();
            params.put(Constants.KICK_OUT_SESSION, true);
            WebUtils.issueRedirect(request,response,redirectUrl,params);
            return false;
        }

        return true;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
