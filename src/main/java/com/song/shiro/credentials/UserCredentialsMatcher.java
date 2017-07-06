package com.song.shiro.credentials;

import com.song.common.Constants;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
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
        //密码输入错误次数限制,超过次数后,限制五分钟后再试
        String username = ((UsernamePasswordToken) token).getUsername();
        BoundValueOperations valueOperations = redisTemplate.boundValueOps(Constants.USER_TRY_LOGIN_TIMES_PRE + username);
        Object o = valueOperations.get();
        if (o != null && Integer.parseInt(o + "") > Constants.USER_CAN_WRONG_PASSWORD_TIMES) {
            throw new ExcessiveAttemptsException();
        }
        boolean b = super.doCredentialsMatch(token, info);
        if (b) {
            redisTemplate.delete(Constants.USER_TRY_LOGIN_TIMES_PRE + username);
        } else {
            if (o == null) {
                valueOperations.set(1);
                valueOperations.expire(300, TimeUnit.SECONDS);
            } else {
                valueOperations.set(Integer.parseInt(o + "") + 1);
                valueOperations.expire(300, TimeUnit.SECONDS);
            }
        }

        return b;
    }
}
