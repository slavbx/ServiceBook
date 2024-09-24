package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OperationTypeService {
    @Autowired
    private final OperationTypeRepository operationTypeRepository;

    @Autowired
    private final OperationService operationService;

    @Autowired
    private final MaintenanceService maintenanceService;

    public void save(OperationType operationType) {
        operationTypeRepository.save(operationType);
    }

    public List<OperationType> findAll(){
        return operationTypeRepository.findAll();
    }

//    public void refreshAllTypeStatus() {
//        Integer currentMileage = maintenanceService.findCurrentMileage();
//        this.findAll().forEach(t ->
////                operationService.findAll().stream()
////                        .filter(o1 -> o1.getType().equals(t) && currentMileage < o1.getMaintenance().getMileage() + o1.getType().getResource())
////                        .findFirst()
////                        .ifPresent(o2 -> t.setStatus("осталось " + (currentMileage - o2.getMaintenance().getMileage() + o2.getType().getResource()) + "км"))
//                  operationService.findAll().stream()
//                          .filter(o1 -> o1.getType().equals(t))
//                          .sorted((o1, o2) -> o2.getMaintenance().getMileage() - o1.getMaintenance().getMileage())
//                          .findFirst()
//                          .ifPresent(o3 -> currentMileage < o3.getMaintenance().getMileage() + o3.getType().getResource() ?
//
//
//                          )
//
//        );
//    }


}
