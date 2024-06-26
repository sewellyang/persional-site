package com.sewell.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewell.common.core.constant.CommonConstants;
import com.sewell.common.core.result.R;
import com.sewell.common.security.repository.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class SiteAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");

        String principal = (String)authentication.getPrincipal();
        R<Object> result = R.ok(jwtUtil.generateToken(principal, principal));

        response.setStatus(CommonConstants.SUCCESS);

        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }


}
