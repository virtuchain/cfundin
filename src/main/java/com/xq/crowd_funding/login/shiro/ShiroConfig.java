package com.xq.crowd_funding.login.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置Shiro过滤器
     * @param securityManager Web安全管理器
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
       // * 我们的案例是前后端分离的，所以下面3个设置不需要设置
        // 设置登录成功跳转Url
       // bean.setSuccessUrl("views/index.html");
       // 设置登录跳转Url
        bean.setLoginUrl("/views/login.html");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/error/unAuth");
        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        //允许登陆请求访问
        filterMap.put("/views/index.html","anon");
        filterMap.put("/views/reg.html","anon");
        filterMap.put("/views/login.html","anon");
        filterMap.put("/login/**","anon");
        filterMap.put("/reg/**","anon");
        //设置运行静态URL路径匿名访问
        filterMap.put("/js/**","anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/jquery/**","anon");
        filterMap.put("/bootstrap/**", "anon");
        filterMap.put("/ztree/**", "anon");
        filterMap.put("/script/**", "anon");
        //其他路径需要登陆访问
        filterMap.put("/**","authc");
        filterMap.put("/logout", "logout");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 自定义Realm配置
     */
    @Bean("userRealm")
    public MyRealm myRealm() {
        MyRealm userRealm = new MyRealm();
        //设置加密
        //userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /**
     * 配置默认的Web安全管理器 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        // 给安全管理器设置自定义的UserRealm实例.
        securityManager.setRealm(myRealm());
        return securityManager;
    }


      //配置Shiro密码加密
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        //指定加密方式为MD5
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        //加密次数1024次
//        credentialsMatcher.setHashIterations(1024);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }


    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)
     * 和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 设置授权注解@RequirePermission ,@hasRole的使用
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        System.out.println("执行AuthorizationAttributeSourceAdvisor==" + securityManager);
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
