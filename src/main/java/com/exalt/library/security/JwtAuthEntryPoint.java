package com.exalt.library.security;

import com.exalt.library.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * handles requests that are missing/have invalid authentication - returns a proper 401 JSON body
 * @author Mohammad Rimawi
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        var body = ApiResponse.error(401, "Unauthorized", "Authentication is required to access this resource");
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}