package com.song.shiro.realm;

import com.song.domain.SysUser;
import com.song.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Song on 2017/6/28.
 */
public class UserRealm extends AuthorizingRealm {
    private Logger log = LogManager.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        try {
            String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
            simpleAuthorizationInfo.setRoles(userService.findRoles(primaryPrincipal));
            simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(primaryPrincipal));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("doGetAuthorizationInfo:" + e);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            if (authenticationToken != null) {
                String userName = (String) authenticationToken.getPrincipal();
                //String password = (String) authenticationToken.getCredentials();
                SysUser user = userService.findByUsername(userName);

                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        userName,
                        user.getPassword(),
                        ByteSource.Util.bytes(user.getCredentialsSalt()),
                        getName());

                return simpleAuthenticationInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("doGetAuthenticationInfo:" + e);
            throw e;
        }
    }
}
