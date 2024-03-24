package com.sewell.common.security.config;

import com.sewell.common.security.filter.SiteAuthenticationFilter;
import com.sewell.common.security.handler.SiteAccessDeniedExceptionHandler;
import com.sewell.common.security.handler.SiteAuthenticationEntryPoint;
import com.sewell.common.security.handler.SiteAuthenticationFailureHandler;
import com.sewell.common.security.handler.SiteAuthenticationSuccessHandler;
import com.sewell.common.security.password.PasswordAuthenticationProvider;
import com.sewell.common.security.properties.SiteSecurityProperties;
import com.sewell.common.security.service.SiteUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

//@ConditionalOnProperty(name = "site.security.enable", value = "true")
@Import(SiteSecurityProperties.class)
@EnableWebSecurity
public class SecurityAutoConfig {

    @Resource
    private SiteSecurityProperties siteSecurityProperties;


    @Bean
    public AccessDeniedHandler siteAccessDeniedExceptionHandler() {
        return new SiteAccessDeniedExceptionHandler();
    }

    @Bean
    public AuthenticationEntryPoint siteAuthenticationEntryPoint() {
        return new SiteAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationSuccessHandler siteAuthenticationSuccessHandler() {
        return new SiteAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler siteAuthenticationFailureHandler() {
        return new SiteAuthenticationFailureHandler();
    }

    @Bean
    public PasswordAuthenticationProvider passwordAuthenticationProvider() {
        return new PasswordAuthenticationProvider();
    }


    @Bean
    public Filter siteSecurityFilter(AuthenticationSuccessHandler siteAuthenticationSuccessHandler,
                                     AuthenticationFailureHandler siteAuthenticationFailureHandler,
                                     AuthenticationManager authenticationManager) {


//        SiteAuthenticationFilter siteAuthenticationFilter = new SiteAuthenticationFilter();
//
//        siteAuthenticationFilter.setAuthenticationFailureHandler(siteAuthenticationFailureHandler);
//        siteAuthenticationFilter.setAuthenticationSuccessHandler(siteAuthenticationSuccessHandler);
//
//        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
//        sharedObject.authenticationProvider(passwordAuthenticationProvider);
//        AuthenticationManager providerManager = sharedObject.build();
//
//
//        siteAuthenticationFilter.setAuthenticationManager(providerManager);
//
//        http.authenticationManager(providerManager);
//        http.addFilterAt(siteAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);




        SiteAuthenticationFilter siteAuthenticationFilter = new SiteAuthenticationFilter();
        siteAuthenticationFilter.setAuthenticationSuccessHandler(siteAuthenticationSuccessHandler);
        siteAuthenticationFilter.setAuthenticationFailureHandler(siteAuthenticationFailureHandler);
        siteAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        siteAuthenticationFilter.setAuthenticationManager(new ProviderManager());
//        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
//        sharedObject.authenticationProvider(passwordAuthenticationProvider);
//        AuthenticationManager providerManager = sharedObject.build();

        return siteAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SiteUserDetails siteUserDetails() {
        return new SiteUserDetails();
    }

    /**
     * HttpSecurity 配置
     *
     * @param http
     * @return {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain siteSecurityFilterChain(HttpSecurity http,
                                                              AccessDeniedHandler siteAccessDeniedExceptionHandler,
                                                              AuthenticationEntryPoint siteAuthenticationEntryPoint,
                                                              PasswordAuthenticationProvider passwordAuthenticationProvider,
                                                              AuthenticationManager authenticationManager,
                                                              Filter siteSecurityFilter
    ) throws Exception {
//ceshishiyong 32434
        //后台功能，基于RBAC的权限认证
        return http.authorizeRequests()
                .antMatchers(siteSecurityProperties.getIgnoreUrls().toArray(new String[]{}))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(siteAccessDeniedExceptionHandler)
                .authenticationEntryPoint(siteAuthenticationEntryPoint)
                .and()
                .addFilterAt(siteSecurityFilter, UsernamePasswordAuthenticationFilter.class)
//                .authenticationManager(authenticationManager)
                .authenticationProvider(passwordAuthenticationProvider)
                .csrf()
                .disable()
                .build();
    }


}
