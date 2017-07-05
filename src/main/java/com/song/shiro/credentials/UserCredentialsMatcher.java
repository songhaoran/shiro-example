package com.song.shiro.credentials;

import com.song.common.Constants;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by Song on 2017/6/28.
 */
public class UserCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //这里可以后续实现登录次数限制
        String username = ((UsernamePasswordToken) token).getUsername();
        BoundValueOperations valueOperations = redisTemplate.boundValueOps(Constants.USER_TRY_LOGIN_TIMES_PRE + username);
        Object o = valueOperations.get();
        boolean b = super.doCredentialsMatch(token, info);
        if (b) {
            redisTemplate.delete(Constants.USER_TRY_LOGIN_TIMES_PRE + username);
        } else {
            if (o == null) {
                valueOperations.set(1);
                valueOperations.expire(300, TimeUnit.SECONDS);
            } else {
                valueOperations.increment(1);
            }
        }
        return b;
    }
}
