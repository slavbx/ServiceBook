package org.slavbx.servicebook.services;


import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.MaintenanceDTO;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final OperationTypeService operationTypeService;

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository, OperationTypeService operationTypeService) {
        this.maintenanceRepository = maintenanceRepository;
        this.operationTypeService = operationTypeService;
    }

    public void save(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> findAll(){
        return maintenanceRepository.findAll().stream().sorted(Comparator.comparing(Maintenance::getDate).reversed()).toList();
    }

    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
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
