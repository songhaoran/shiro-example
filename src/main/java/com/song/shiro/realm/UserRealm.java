package com.song.shiro.realm;

import com.song.domain.SysUser;
import com.song.service.UserService;
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
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal= (String)principalCollection.getPrimaryPrincipal();
        SysUser user = userService.findByUsername(primaryPrincipal);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.findRoles(primaryPrincipal));
        simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(primaryPrincipal));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
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
    }
}
