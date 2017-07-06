package com.song.shiro.filter;

import com.song.common.Constants;
import com.song.domain.SysUser;
import com.song.listener.LocalHttpSessionContext;
import com.song.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Song on 2017/6/30.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    private Logger log = LogManager.getLogger(CustomFormAuthenticationFilter.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        boolean b = false;
        try {
            //登录成功后,将用户放入session
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpSession session = httpServletRequest.getSession();
            SysUser sysUser = userService.findByUsername(getUsername(request));
            session.setAttribute(Constants.CURRENT_USER, sysUser);

            //放入缓存,用来控制并发登陆人数
            BoundListOperations listOperations = redisTemplate.boundListOps(sysUser.getUsername());
            Long size = listOperations.size();
            //踢出最早登陆的用户
            if (size >= Constants.EVERY_ACCOUNT_CURRENT_LOGINED_NUM) {
                Object kickOutSessionId = listOperations.leftPop();
                HttpSession kickOutSession = LocalHttpSessionContext.getSession(kickOutSessionId + "");
                if (kickOutSession != null) {
                    kickOutSession.setAttribute(Constants.KICK_OUT_SESSION, true);
                }
            }

            List<String> list = listOperations.range(0, size);
            if (!list.contains(session.getId())) {
                listOperations.rightPush(session.getId());
            }

            b = super.onLoginSuccess(token, subject, request, response);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
        return b;
    }

}
