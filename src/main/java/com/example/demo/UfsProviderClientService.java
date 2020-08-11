package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class UfsProviderClientService {

    @Autowired
    HttpServletRequest request;

    public String getUserInfo(String url){
        try {
            return getResponse(url, String.class);
        }catch (Exception e){
            return null;
        }
    }

    private <ReturnType> ReturnType getResponse(String url, Class<ReturnType> returnTypeClass) {
        return processResponse(url, HttpMethod.GET, returnTypeClass,null);
    }

    private <ReturnType, BodyType> ReturnType postResponse(String url, Class<ReturnType> returnTypeClass, BodyType body) {
        return processResponse(url, HttpMethod.GET, returnTypeClass,body);
    }

    private <ReturnType, BodyType> ReturnType processResponse(String url, HttpMethod method, Class<ReturnType> returnTypeClass, BodyType body){
        RestTemplate restTemplate = new RestTemplate();

        String sessionCookie = getSessionCookie();
        HttpHeaders requestHeaders = new HttpHeaders();

        if(sessionCookie!= null && sessionCookie.length()>0) {
            requestHeaders.add("Cookie", "UFS-SESSION=" + sessionCookie);
        }

        HttpEntity requestEntity = new HttpEntity(body, requestHeaders);
        return restTemplate.exchange(url, method, requestEntity, returnTypeClass).getBody();
    }

    private String getSessionCookie(){
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            return "";
        for ( int i=0; i<cookies.length; i++) {

            Cookie cookie = cookies[i];
            if(cookie.getName().equalsIgnoreCase("ufs-session"))
                return cookie.getValue();
        }

        return "";
    }
}
