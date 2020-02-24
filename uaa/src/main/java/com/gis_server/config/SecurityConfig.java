package com.gis_server.config;


import com.gis_server.config.handler.CustomLogoutSuccessHandler;
import com.gis_server.filter.MyCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Resource
    private MyCorsFilter myCorsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .cors()
//                .and()
//                .authorizeRequests()
////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/druid/**").permitAll()
////                .antMatchers("/oauth/**").permitAll()
//////                .antMatchers("/r/r1").hasAuthority("p1")
////                .antMatchers("/login*").permitAll()
//                .anyRequest().authenticated();
////                .and()
////                .formLogin();

        http.cors().and().csrf().disable();
        http.formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .deleteCookies("UAA_SESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * 密码加密策略
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
//            return new MessageDigestPasswordEncoder("MD5");
    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
