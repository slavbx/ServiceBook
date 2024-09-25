package org.slavbx.servicebook.services;


import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.MaintenanceDTO;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.MaintenanceRepository;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor //Аннотация autowired не потребуется при создании конструктора для одного поля final
public class MaintenanceService {
    @Autowired
    private final MaintenanceRepository maintenanceRepository;
    @Autowired
    private final OperationTypeService operationTypeService;

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

    public void createMaintenance(MaintenanceDTO maintenanceDTO) {
        List<OperationType> operationTypes = operationTypeService.findAllById(maintenanceDTO.getSelectedOperationTypeIds());
        Maintenance maintenance = Maintenance.builder() //Заготовка, осталось добавить список operations
                .mileage(maintenanceDTO.getMileage())
                .date(maintenanceDTO.getDate())
                .build();

        List<Operation> operations = new ArrayList<>();
        for(OperationType operationType: operationTypes) {
            operations.add(Operation.builder()
                    .maintenance(maintenance)
                    .type(operationType)
                    .description(operationType.getName())
                    .build());
        }
        maintenance.setOperations(operations);
        maintenanceRepository.save(maintenance);
    }
}
