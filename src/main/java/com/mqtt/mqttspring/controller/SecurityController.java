package com.mqtt.mqttspring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {

    @GetMapping("/csrf-token")
    public Map<String, String> csrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", csrfToken.getToken());
        tokenMap.put("parameterName", csrfToken.getParameterName());
        tokenMap.put("headerName", csrfToken.getHeaderName());
        return tokenMap;
    }

}
