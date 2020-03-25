package com.gis_server.config.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "CHAT";


    @Autowired
    TokenStore tokenStore;

//    @Autowired
//    CustomAccessDeniedHandler customAccessDeniedHandler;
//
//    @Autowired
//    CustomLogoutSuccessHandler customLogoutSuccessHandler;
//
//    @Autowired
//    AuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId((RESOURCE_ID))
                .tokenStore(tokenStore)
//                .tokenServices(tokenServices())
                .stateless(true);
//                .authenticationEntryPoint(authExceptionEntryPoint)
//                .accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable();
        http
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(customLogoutSuccessHandler)
//                .deleteCookies("CHAT_SESSIONID")
//                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/callback*").permitAll()
                .antMatchers("/chatroom*").permitAll()
                .antMatchers("/chat/**").permitAll()
                .antMatchers("/css/**", "/emoji_plugin/**", "/head_img/**", "/img/**", "/js/**", "/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}
