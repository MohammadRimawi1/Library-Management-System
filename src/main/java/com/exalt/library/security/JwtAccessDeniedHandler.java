package com.exalt.library.security;

import com.exalt.library.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * handles requests that are authenticated but lack the required role - returns a proper 403 JSON body
 * @author Mohammad Rimawi
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        var body = ApiResponse.error(403, "Forbidden", "You do not have permission to access this resource");
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}