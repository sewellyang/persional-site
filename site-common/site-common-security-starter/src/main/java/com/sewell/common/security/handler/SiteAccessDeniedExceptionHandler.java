package com.sewell.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewell.common.core.result.R;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class SiteAccessDeniedExceptionHandler implements AccessDeniedHandler {
    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (response.isCommitted()) {
            logger.trace("Did not write to response since already committed");
            return;
        }

        // Put exception into request scope (perhaps of use to a view)
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        // Set the 403 status code.
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // forward to error page.
        R<Object> result = R.failed(HttpStatus.FORBIDDEN.value(),Optional
                .ofNullable(accessDeniedException)
                .map(Throwable::getMessage)
                .orElse(""));

        PrintWriter printWriter = response.getWriter();

        printWriter.append(objectMapper.writeValueAsString(result));
    }

}
