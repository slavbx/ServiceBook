package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.slavbx.servicebook.models.Car;
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
    private final CarService carService;

    public void save(OperationType operationType) {
        operationTypeRepository.save(operationType);
    }

    public List<OperationType> findAll(){
        return operationTypeRepository.findAll();
    }

    public void refreshAllTypeStatus() {
        Car car = carService.getCar();
        for(OperationType operationType: this.findAll()) {
            Operation operation =  operationService.getOperationOfMaxMileageByType(operationType);
            int deltaMileage = operation.getMaintenance().getMileage() + operationType.getResource() - car.getMileage();
            if (deltaMileage > 0) {
                operationType.setStatus("запас " + deltaMileage + " км");
            } else {
                operationType.setStatus("превышено " + Math.abs(deltaMileage) + " км");
            }
        }
    }

}
