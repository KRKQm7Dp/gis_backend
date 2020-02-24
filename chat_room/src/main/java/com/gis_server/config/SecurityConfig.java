package com.gis_server.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;


//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableOAuth2Sso
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//        http.cors().and().csrf().disable();
////        http.authorizeRequests()
////                .antMatchers("/webjars/**").permitAll()
////                .antMatchers("/login").permitAll()
//////                .antMatchers("/chat/index").permitAll()
//////                .antMatchers("/**").permitAll()
//////                .antMatchers("/chat/login").permitAll()
////                .anyRequest().authenticated();
//        http.antMatcher("/**").authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated();
////        http.formLogin().successForwardUrl("/chat/callback");
//    }
//
//}
