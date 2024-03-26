package com.sewell.common.security.repository;

import com.sewell.common.security.password.PasswordAuthenticationToken;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.StringUtils;

public class JwtRepository implements SecurityContextRepository {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();

        SecurityContextImpl securityContext = getSecurityContext(request);
        return securityContext;
    }

    private SecurityContextImpl getSecurityContext(HttpServletRequest request) {
        SecurityContextImpl securityContext = new SecurityContextImpl();
        String token = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.hasText(token)) {
            String username = jwtUtil.getUsename(token);

            securityContext.setAuthentication(PasswordAuthenticationToken.authenticated(username, "", null));
        }
        return securityContext;
    }

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        return SecurityContextRepository.super.loadDeferredContext(request);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {

        return false;
    }
}
