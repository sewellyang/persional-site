package com.sewell.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewell.common.core.exception.BusinessException;
import com.sewell.common.security.password.PasswordAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Map;

public class SiteAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 认证类型，字段名
     */
    private static final String GRANT_TYPE_NAME = "grant";
    enum GrantType {
        PASSWORD;
    }


    public SiteAuthenticationFilter() {
        super("/login2");
    }

    public SiteAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //POST和GET请求都可以做认证

        String grant;
        Map<String, String> reqMap = null;

        boolean isGet = HttpMethod.GET.name().equals(request.getMethod());

        //如果是get请求
        if (isGet) {
            grant = request.getParameter(GRANT_TYPE_NAME);
        } else {
            reqMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            grant = reqMap.get(GRANT_TYPE_NAME);
        }

        //根据不同的grant，获取不同的Map
        GrantType grantType = GrantType.valueOf(grant.toUpperCase());
        if (isGet) {
            if (grantType == GrantType.PASSWORD) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                PasswordAuthenticationToken passwordAuthenticationToken = PasswordAuthenticationToken.unauthenticated(username, password);
                //将sessionId和remoteAddress存储到Token信息中
                passwordAuthenticationToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
                return this.getAuthenticationManager().authenticate(passwordAuthenticationToken);
            }
        } else {
            switch (grantType) {
                case PASSWORD -> {
                    String username = reqMap.get("username");
                    String password = reqMap.get("password");
                    PasswordAuthenticationToken passwordAuthenticationToken = PasswordAuthenticationToken.unauthenticated(username, password);
                    //将sessionId和remoteAddress存储到Token信息中
                    passwordAuthenticationToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
                    return this.getAuthenticationManager().authenticate(passwordAuthenticationToken);
                }
            }
        }


        throw new BusinessException("500");
    }

}
