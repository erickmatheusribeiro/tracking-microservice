package com.tracking.management.system.trackingmicroservice.interfaceadapters.controller;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.viacep.ViaCepResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ViaCepController {

    private static HttpHeaders getHttpHeaders() {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }


    public ViaCepResponse validCep(String cep){
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViaCepResponse> responseEntity = restTemplate.exchange(
                "https://viacep.com.br/ws/" + cep + "/json",
                HttpMethod.GET,
                requestEntity,
                ViaCepResponse.class
        );

        return responseEntity.getBody();
    }

}
