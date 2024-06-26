package com.sewell.common.security.config;

import com.sewell.common.security.filter.SiteAuthenticationFilter;
import com.sewell.common.security.handler.SiteAccessDeniedExceptionHandler;
import com.sewell.common.security.handler.SiteAuthenticationEntryPoint;
import com.sewell.common.security.handler.SiteAuthenticationFailureHandler;
import com.sewell.common.security.handler.SiteAuthenticationSuccessHandler;
import com.sewell.common.security.password.PasswordAuthenticationProvider;
import com.sewell.common.security.properties.SiteSecurityProperties;
import com.sewell.common.security.repository.JwtRepository;
import com.sewell.common.security.repository.JwtUtil;
import com.sewell.common.security.service.SiteUserDetails;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@ConditionalOnProperty(name = "site.security.enable", havingValue = "true")
@Import(SiteSecurityProperties.class)
//@Configuration(proxyBeanMethods = false)
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
    public JwtRepository jwtRepository() {
        return new JwtRepository();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public Filter siteSecurityFilter(AuthenticationSuccessHandler siteAuthenticationSuccessHandler,
                                     AuthenticationFailureHandler siteAuthenticationFailureHandler,
                                     AuthenticationManager authenticationManager,
                                     JwtRepository jwtRepository) {


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
//        siteAuthenticationFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
        siteAuthenticationFilter.setSecurityContextRepository(jwtRepository);
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
                                                              Filter siteSecurityFilter,
                                                       JwtRepository jwtRepository
    ) throws Exception {
        //后台功能，基于RBAC的权限认证
        return http.authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests.requestMatchers(siteSecurityProperties.getIgnoreUrls().toArray(new String[]{}))
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedHandler(siteAccessDeniedExceptionHandler)
                                .authenticationEntryPoint(siteAuthenticationEntryPoint)
                )
                .securityContext((securityContext)->securityContext.securityContextRepository(jwtRepository))

                .addFilterAt(siteSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(passwordAuthenticationProvider)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


}
