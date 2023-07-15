/*

package com.helf.filters;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.helf.config.Configurations;
import com.helf.dto.Constants;
import com.helf.dto.Errors;
import com.helf.entity.SecureUser;
import com.helf.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class RBACFilter implements Filter {

    @Autowired
    DoctorService roleActionService;

    @Autowired
    ObjectMapper objectMapper;

    private FilterConfig filterConfig;

    @Autowired
    private Configurations configurations;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        String endpoint = ((HttpServletRequest)request).getRequestURI();  // get the full URL
        String contextPath = ((HttpServletRequest)request).getContextPath();  // get the context path
        endpoint = endpoint.substring(contextPath.length());  // remove the context path from the URL
        if (!(configurations.getOAuthEndpoint().contains(endpoint))) {
            List<String> actions = authorizeRequest(request, response, url);
            String finalEndpoint = endpoint.replaceAll("/\\d+", "");
            List<String> actionList=actions.stream().filter(a->a.contains(finalEndpoint)).collect(Collectors.toList());
            if (actions.size() == 0 || actionList.size()==0) {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
                ((HttpServletResponse) response).setContentType(Constants.APPLICATION_JSON);
                ((HttpServletResponse) response).setCharacterEncoding(StandardCharsets.UTF_8.name());
                Errors error=   Errors.builder().errorCode(Constants.UNAUTHORIZED_CODE).errorMessage(Constants.UNAUTHORIZED_MESSAGE).build();
                ((HttpServletResponse) response).getWriter().write(objectMapper.writeValueAsString(error));
                return;
            } else {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }

    }

    public List<String> authorizeRequest(ServletRequest request, ServletResponse response,String url){
        SecureUser prinipal=   (SecureUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<String> actions=roleActionService.getUserByUsername(prinipal.getUser().getUserName());
        List<String> actions=null;
        return actions;
    }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
}

*/
