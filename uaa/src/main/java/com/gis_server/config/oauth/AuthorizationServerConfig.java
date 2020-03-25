package com.gis_server.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;


    /**
     * 客户端详情服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
//            clients.inMemory()    // 使用内存存储
//                .withClient("c1")    // 客户端名称
//                .secret(new MessageDigestPasswordEncoder("MD5").encode("secret"))    // 密钥,必须和 SecurityConfig 中配置的密码加密策略保持一致
//                .resourceIds("order")    // 允许访问的资源
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")    // 允许的oauth授权类型
//                .scopes("all")    // 允许的授权范围
//                .autoApprove(false)    // 跳转到授权页面
//                .redirectUris("http://www.baidu.com");
    }

    /**
     * 令牌访问端点和令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)    // 密码模式
                .authorizationCodeServices(authorizationCodeServices)    // 授权码服务
                .tokenServices(tokenServices())    // 令牌管理模式
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }

    /**
     * 令牌端点安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         *  /oauth/authorize : 授权端点
         *  /oauth/token : 令牌端点
         *  /oauth/confirm_access : 用户确认授权提交端点
         *  /oauth/error : 授权服务错误信息端点
         *  /oauth/check_token : 用于资源服务访问的令牌解析端点
         *  /oauth/token_key : 提供公钥的端点，如果使用的是JWT令牌。
         *
         */
        security.tokenKeyAccess("permitAll()")    // 公钥公开
                .checkTokenAccess("permitAll()")    // 检测token公开
                .allowFormAuthenticationForClients();    // 表单认证
    }

    /**
     * 令牌管理服务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);  // 客户端详情服务
        defaultTokenServices.setSupportRefreshToken(true);   // 支持刷新令牌
        defaultTokenServices.setTokenStore(tokenStore);  // 令牌存储策略
        // 令牌增强
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(jwtTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);
        defaultTokenServices.setTokenEnhancer(enhancerChain);

        defaultTokenServices.setAccessTokenValiditySeconds(7200); // 两小时
        defaultTokenServices.setRefreshTokenValiditySeconds(259200); // 3天
        return defaultTokenServices;
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 授权码的使用模式
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
//         return new InMemoryAuthorizationCodeServices();
        return new JdbcAuthorizationCodeServices(dataSource);
    }

}
