package com.gis_server.config.oauth;


import com.gis_server.config.handler.AuthExceptionEntryPoint;
import com.gis_server.config.handler.CustomAccessDeniedHandler;
import com.gis_server.config.handler.CustomLogoutSuccessHandler;
import com.gis_server.filter.MyCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "order";


    @Autowired
    TokenStore tokenStore;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

//    @Autowired
//    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId((RESOURCE_ID))
                .tokenStore(tokenStore)
//                .tokenServices(tokenServices())
                .stateless(true)
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler);
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http.cors().and().csrf().disable();
//        http.formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(customLogoutSuccessHandler)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/druid/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
//                .antMatchers("/login*").permitAll()
//                .anyRequest().authenticated();
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }


//    @Bean
//    public ResourceServerTokenServices tokenServices(){
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        remoteTokenServices.setClientId("c1");
//        remoteTokenServices.setClientSecret("secret");
//        return remoteTokenServices;
//    }


}
