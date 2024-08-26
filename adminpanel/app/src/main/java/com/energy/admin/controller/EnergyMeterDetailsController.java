package com.energy.admin.controller;

import com.energy.admin.model.EnergyMeter;
import com.energy.admin.service.EnergyMeterService;
import com.energy.admin.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy-meter-details")
public class EnergyMeterDetailsController {

    @Autowired
    private EnergyMeterService energyMeterDetailsService;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/search")
    public ResponseEntity<List<EnergyMeter>> searchEnergyMeterDetails(@RequestParam String query) {
        List<EnergyMeter> meters = energyMeterDetailsService.searchEnergyMeterDetails(query);
        return ResponseEntity.ok(meters);
    }

    @GetMapping("/s3-file")
    public ResponseEntity<String> getFileFromS3(@RequestParam String bucketName, @RequestParam String key) {
        String downloadPath = "/storage/" + key; 
        String result = s3Service.downloadFile(bucketName, key, downloadPath);
        return ResponseEntity.ok(result);
    }
}
