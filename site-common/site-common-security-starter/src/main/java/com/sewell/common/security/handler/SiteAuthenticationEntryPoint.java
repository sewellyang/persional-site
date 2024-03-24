package com.sewell.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewell.common.core.result.R;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class SiteAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Put exception into request scope (perhaps of use to a view)
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, authException);
        // Set the 403 status code.
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // forward to error page.
        R<Object> result = R.failed(HttpStatus.FORBIDDEN.value(),Optional
                .ofNullable(authException)
                .map(Throwable::getMessage)
                .orElse(""));

        PrintWriter printWriter = response.getWriter();

        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
