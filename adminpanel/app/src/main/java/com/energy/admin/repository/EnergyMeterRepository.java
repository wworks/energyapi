package com.energy.admin.repository;

import com.energy.admin.model.EnergyMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnergyMeterRepository extends JpaRepository<EnergyMeter, Long> {
    List<EnergyMeter> findByLocationContaining(String query);
}
