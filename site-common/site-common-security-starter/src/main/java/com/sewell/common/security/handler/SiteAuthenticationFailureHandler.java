package com.sewell.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewell.common.core.result.R;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class SiteAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");

        R<Object> result = R.failed(HttpStatus.UNAUTHORIZED.value(), Optional
                .ofNullable(exception)
                .map(Throwable::getMessage)
                .orElse(""));

        response.setStatus(HttpStatus.UNAUTHORIZED.value());


        PrintWriter printWriter = response.getWriter();

        printWriter.append(objectMapper.writeValueAsString(result));

    }
}
