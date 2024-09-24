package org.slavbx.servicebook.services;


import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.MaintenanceRepository;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor //Аннотация autowired не потребуется при создании конструктора для одного поля final
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public void save(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> findAll(){
        return maintenanceRepository.findAll();
    }

    public Integer findCurrentMileage() {
        //Найти maintenance с максимальным пробегом и вернуть его
        return this.findAll().stream().max(Comparator.comparing(Maintenance::getMileage)).map(Maintenance::getMileage).orElse(0);
    }
}
