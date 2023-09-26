//package com.exam.examserver.justfortry;
//
//import com.exam.examserver.entity.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//@Component
//
//@Order(1)
//public class loggings implements Filter {
//    @Autowired
//    ObjectMapper objectMapper;
//    private final Logger logger = LoggerFactory.getLogger(loggings.class);
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//        String uri = httpServletRequest.getRequestURI();
//        logger.info("Request URL: {}", uri);
//        logger.info("Request Method: {}", httpServletRequest.getMethod());
//
//        // Read and deserialize JSON data using ObjectMapper
//        try {
//            BufferedReader reader = httpServletRequest.getReader();
//            StringBuilder requestDataBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                requestDataBuilder.append(line);
//            }
//            String requestData = requestDataBuilder.toString();
//
//            if ("/user/create".equalsIgnoreCase(uri)) {
//                User user = objectMapper.readValue(requestData, User.class);
//                user.setPassword("****");
//                requestData = objectMapper.writeValueAsString(user);
//            }
//
//            logger.info("Request Body: {}", requestData);
//        } catch (Exception e) {
//            // Handle exceptions here, e.g., JSON parsing errors
//            logger.error("Error processing request: {}", e.getMessage());
//        }
//
//        // Continue with the filter chain
//        chain.doFilter(request, response);
//
//        // ... rest of your code ...
//    }
//
//
//}