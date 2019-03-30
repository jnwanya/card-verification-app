package com.jumia.jumiapay.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Slf4j
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequest(httpRequest, bytes);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        if (log.isDebugEnabled()) {
            // HttpHeaders httpHeaders = request.getHeaders();
            // log.debug("Headers     : {}", request.getHeaders());
            log.debug("===request begin: {} =====", new Date());
            //  log.debug("URI         : {}", request.getURI());
            log.debug("Method/URI     : {} / {}", request.getMethod(), request.getURI());
            //  log.debug("Headers     : {}", request.getHeaders());
            log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        if (log.isDebugEnabled()) {
            // log.debug("Request completed on: "+PortalUtil.formatDate(new Date(),"HH:mm:ss"));
            //  log.debug("==============response obtained: {}==================", endTime);
            log.debug("Status code/Text : {} / {}", response.getStatusCode() , response.getStatusText());
            // log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log.debug("===response obtained: {}====", new Date());
        }
    }
}
