package com.exam.examserver.filter;

import com.exam.examserver.controller.UserController;
import com.exam.examserver.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.io.IOUtils;

import jakarta.servlet.http.HttpServletRequestWrapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;

@Component

@Order(1)
public class RequestResponseLogger implements Filter {

    @Autowired
    ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(RequestResponseLogger.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
//       logger.info("Request URL: {}", httpServletRequest.getRequestURL());
//       logger.info("Request Method:{}", httpServletRequest.getMethod());
//        logger.info("Request Body:{}", new String(IOUtils.toByteArray( httpServletRequest.getInputStream())));

        MyCustomHttpRequestWrapper requestWrapper = new MyCustomHttpRequestWrapper((HttpServletRequest) request);

        String uri = requestWrapper.getRequestURI();
//        logger.info("Request URL: {}", requestWrapper.getRequestURL());
        logger.info("Request URL: {}", uri);

        logger.info("Request Method:{}", requestWrapper.getMethod());
        String requestData=new String(requestWrapper.getByteArrays());


        if("/user/create".equalsIgnoreCase(uri)){
            User user = objectMapper.readValue(requestData, User.class);
            user.setPassword("****");
            requestData=objectMapper.writeValueAsString(user);

        }
        logger.info("Request Body:{}", requestData);

        MyCustomHttpResponseWrapper responseWrapper =new MyCustomHttpResponseWrapper((HttpServletResponse)response);

        chain.doFilter(requestWrapper, responseWrapper);




        String responseResult=new String(responseWrapper.getBaos().toByteArray());

        logger.info("Response status - {}",responseWrapper.getStatus());
        logger.info("Response Body - {}", responseResult );

        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
    }
    @Getter
    private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper{

        /**
         * Create a new {@code HttpRequest} wrapping the given request object.
         *
         * @param request the request object to be wrapped
         */
       private byte[] byteArrays;
        public MyCustomHttpRequestWrapper(HttpServletRequest request) {
            super(request);
            try {
                byteArrays=IOUtils.toByteArray(request.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArrays));
        }

        public byte[] getByteArrays() {
            return byteArrays;
        }


    }
@Getter
    private class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {
        @Getter
        private ByteArrayOutputStream baos=new ByteArrayOutputStream();
       private PrintStream printStream=new PrintStream(baos);

        public MyCustomHttpResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
           return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(),printStream));

        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new TeeOutputStream(super.getOutputStream(),printStream));

        }
    }
}
