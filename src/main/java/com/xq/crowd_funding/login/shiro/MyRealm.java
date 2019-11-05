package com.xq.crowd_funding.login.shiro;


import com.xq.crowd_funding.login.bean.service.IService;
import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登陆和授权 Realm
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    IService servicejk;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //封装用户角色与权限信息返回给shiro
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    /**
     * 登陆认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户信息
        TMember tMember = servicejk.selectUser(token.getUsername());
        if (tMember == null) {
            throw new UnknownAccountException("用户不存在！！");
        }

        //封装用户认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(tMember, tMember.getUserpswd(), getName());
        return info;
    }
}
