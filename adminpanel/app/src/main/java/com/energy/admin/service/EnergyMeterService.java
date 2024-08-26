package com.energy.admin.service;

import com.energy.admin.model.EnergyMeter;
import com.energy.admin.repository.EnergyMeterRepository;
import com.energy.admin.repository.EnergyMeterDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyMeterService {

    @Autowired
    private EnergyMeterRepository energyMeterRepository;

    @Autowired
    private EnergyMeterDetailsRepository energyMeterDetailsRepository;

    public List<EnergyMeter> searchEnergyMeterDetails(String searchQuery) {
        return energyMeterDetailsRepository.findByMeterDetails(searchQuery);
    }

    public List<EnergyMeter> searchEnergyMeters(String query) {
        return energyMeterRepository.findByLocationContaining(query);
    }

    public List<EnergyMeter> getAllEnergyMeters() {
        return energyMeterRepository.findAll();
    }

    public EnergyMeter saveEnergyMeter(EnergyMeter energyMeter) {
        // Sanitize location before saving
        energyMeter.setLocation(sanitizeLocation(energyMeter.getLocation()));
        return energyMeterRepository.save(energyMeter);
    }

    public EnergyMeter updateEnergyMeter(Long id, EnergyMeter energyMeterDetails) {
        EnergyMeter energyMeter = energyMeterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EnergyMeter not found"));

        // Update relevant fields
        energyMeter.setLocation(sanitizeLocation(energyMeterDetails.getLocation()));
        energyMeter.setStatus(energyMeterDetails.isStatus());  // Set boolean status

        return energyMeterRepository.save(energyMeter);
    }

    public void deleteEnergyMeter(Long id) {
        energyMeterRepository.deleteById(id);
    }

    // Methode voor inputvalidatie (XSS-filter)
    private String sanitizeLocation(String input) {
        if (input == null) {
            return null;
        }
        //input = input.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
       
        return input;
    }

}
