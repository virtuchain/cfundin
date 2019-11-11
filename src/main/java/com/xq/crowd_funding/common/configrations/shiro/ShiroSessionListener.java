package com.xq.crowd_funding.common.configrations.shiro;/*
    @auther yangjie
*/

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * session 监听类，当session 开始，失效 ，异常。处理
 */
public class ShiroSessionListener implements SessionListener{

    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {

    }
}
