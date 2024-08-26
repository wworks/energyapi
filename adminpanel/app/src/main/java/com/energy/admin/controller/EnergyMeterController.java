package com.energy.admin.controller;

import com.energy.admin.model.EnergyMeter;
import com.energy.admin.service.EnergyMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy-meters")
public class EnergyMeterController {

    private final EnergyMeterService energyMeterService;

    @Autowired
    public EnergyMeterController(EnergyMeterService energyMeterService) {
        this.energyMeterService = energyMeterService;
    }

    @GetMapping("/search")
    public List<EnergyMeter> searchEnergyMeters(@RequestParam String query) {
        return energyMeterService.searchEnergyMeters(query);
    }

    @GetMapping
    public List<EnergyMeter> getAllEnergyMeters() {
        return energyMeterService.getAllEnergyMeters();
    }
    @PostMapping
    public EnergyMeter addEnergyMeter(@RequestBody EnergyMeter energyMeter) {
        return energyMeterService.saveEnergyMeter(energyMeter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyMeter> updateEnergyMeter(@PathVariable Long id, @RequestBody EnergyMeter energyMeterDetails) {
        EnergyMeter updatedEnergyMeter = energyMeterService.updateEnergyMeter(id, energyMeterDetails);
        return ResponseEntity.ok(updatedEnergyMeter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnergyMeter(@PathVariable Long id) {
        energyMeterService.deleteEnergyMeter(id);
        return ResponseEntity.noContent().build();
    }
}
