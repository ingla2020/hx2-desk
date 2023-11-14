package com.hx2.desk.config;

import java.io.IOException;
import java.io.PrintWriter;


import com.hx2.desk.dto.ApiError;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

    public ObjectMapper mapper;

    public CustomBasicAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
        String strApiError = mapper.writeValueAsString(apiError);

        PrintWriter writer = response.getWriter();
        writer.println(strApiError);
    }

    @PostConstruct
    public void initRealname() {
        setRealmName("la.net");
    }

}