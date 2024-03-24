package com.sewell.common.security.filter;//package com.sewell.server.auth.filter;
//
//import org.springframework.core.log.LogMessage;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.ExceptionTranslationFilter;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.util.ThrowableAnalyzer;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class SiteExceptionHandlerFilter extends ExceptionTranslationFilter {
//
//    private ThrowableAnalyzer throwableAnalyzer = new ThrowableAnalyzer();
//
//    public SiteExceptionHandlerFilter(AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationEntryPoint);
//    }
//
//    public SiteExceptionHandlerFilter(AuthenticationEntryPoint authenticationEntryPoint, RequestCache requestCache) {
//        super(authenticationEntryPoint, requestCache);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            chain.doFilter(request, response);
//        }
//        catch (IOException ex) {
//            throw ex;
//        }
//        catch (Exception ex) {
//            //判断错误是否是AccessDeniedException
//            // Try to extract a SpringSecurityException from the stacktrace
//            Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(ex);
//            RuntimeException securityException = (AuthenticationException) this.throwableAnalyzer
//                    .getFirstThrowableOfType(AuthenticationException.class, causeChain);
//            if (securityException == null) {
//                securityException = (AccessDeniedException) this.throwableAnalyzer
//                        .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
//            }
//            //如果不是 AccessDeniedException 类异常
//            if (securityException == null) {
//                //TODO 统一处理
//            }
//            if (response.isCommitted()) {
//                throw new ServletException("Unable to handle the Spring Security Exception "
//                        + "because the response is already committed.", ex);
//            }
//            handleSpringSecurityException(request, response, chain, securityException);
//        }
//    }
//
//    private void handleSpringSecurityException(HttpServletRequest request, HttpServletResponse response,
//                                               FilterChain chain, RuntimeException exception) throws ServletException, IOException {
////        if (exception instanceof AuthenticationException) {
////            handleAuthenticationException(request, response, chain, (AuthenticationException) exception);
////        }
////        else if (exception instanceof AccessDeniedException) {
////            handleAccessDeniedException(request, response, chain, (AccessDeniedException) exception);
////        }
//
//       //认证异常
//        if (exception instanceof AuthenticationException) {
//            this.logger.trace("Sending to authentication entry point since authentication failed", exception);
//            sendStartAuthentication(request, response, chain, (AuthenticationException) exception);
//        }
//        else if (exception instanceof AccessDeniedException) {
//            //权限异常
////            handleAccessDeniedException(request, response, chain, (AccessDeniedException) exception);
//            if (logger.isTraceEnabled()) {
//                logger.trace(
//                        LogMessage.format("Sending %s to access denied handler since access is denied", authentication),
//                        exception);
//            }
//            this.accessDeniedHandler.handle(request, response, exception);
//        }
//    }
//}
