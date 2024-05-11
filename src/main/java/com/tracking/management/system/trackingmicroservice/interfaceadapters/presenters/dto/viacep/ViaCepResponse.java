package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto.viacep;

import lombok.Data;

@Data
public class ViaCepResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
