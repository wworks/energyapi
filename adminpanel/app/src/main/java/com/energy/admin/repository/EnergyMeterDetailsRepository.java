package com.energy.admin.repository;

import com.energy.admin.model.EnergyMeter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnergyMeterDetailsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EnergyMeter> findByMeterDetails(String searchQuery) {
        // Kwetsbare query, gevoelig voor SQL-injectie
        String query = "SELECT * FROM energy_meters WHERE id = '" + searchQuery + "'";
        return entityManager.createNativeQuery(query, EnergyMeter.class).getResultList();
    }
}
