package com.energy.admin.controller;

import com.energy.admin.service.EnergyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyDataService energyDataService;

    @Autowired
    public EnergyController(EnergyDataService energyDataService) {
        this.energyDataService = energyDataService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getEnergyData() {
        try {
            String data = energyDataService.fetchEnergyData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to fetch energy data: " + e.getMessage());
        }
    }
}
