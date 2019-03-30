package com.jumia.jumiapay.web.util;

import com.jumia.jumiapay.web.pojo.CardResolutionResponse;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Service
public class RestServiceClient {

    private final RestTemplate restTemplate;
    public RestServiceClient() {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
    }

    public <T> ResponseEntity<T> getApiRequestResponse(String serviceUrl, Class<T> tClass){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        try{
            //ResponseEntity<String> responseEntity = restTemplate.getForEntity(serviceUrl, String.class);
            ResponseEntity<T> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, entity, tClass);
            System.out.println("Status Code: "+responseEntity.getStatusCodeValue());
            System.out.println(responseEntity.getBody());
            return responseEntity;
        }catch (HttpStatusCodeException e){
            ResponseEntity<T> responseEntity = new ResponseEntity<>(e.getStatusCode());
            System.out.println("Status Code: "+responseEntity.getStatusCodeValue());
            return responseEntity;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
