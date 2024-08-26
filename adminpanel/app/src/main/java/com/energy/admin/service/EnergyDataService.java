package com.energy.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnergyDataService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://energy-api.docker.localhost";  // Base URL of the FastAPI server

    @Autowired
    public EnergyDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchEnergyData() {
        String url = baseUrl + "/energy_data/";
        return restTemplate.getForObject(url, String.class);
    }
}

