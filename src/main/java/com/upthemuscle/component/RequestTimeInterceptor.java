package com.upthemuscle.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("RequestTimeInterceptor")
public class RequestTimeInterceptor implements HandlerInterceptor {

    private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

    //Se va a ejecutar primero antes de entrar en el metodo del controlador
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    //Se va a ejecutar segundo justo antes de mostrar la vista del navegador
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        //LOG.info("--REQUEST  URL: '" + request.getRequestURL().toString()+ "' -- TOTAL TIME: '" + (System.currentTimeMillis() - startTime) + "' ms");
        LOG.info("Url to: '" + request.getRequestURL().toString()+ "' -- in: '" + (System.currentTimeMillis() - startTime) + "' ms");
    }
}
