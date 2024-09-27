package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с сущностью OperationType
 */
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
            Optional<Operation> optionalOperation = operationService.getOperationOfMaxMileageByType(operationType);
            int deltaMileage;
            if (optionalOperation.isPresent()) {
                deltaMileage = optionalOperation.get().getMaintenance().getMileage() + operationType.getResource() - car.getMileage();
            } else {
                deltaMileage = operationType.getResource() - car.getMileage();
            }
            if (deltaMileage > 0) {
                operationType.setStatus("запас " + deltaMileage + "км");
            } else {
                operationType.setStatus("превышено " + Math.abs(deltaMileage) + "км");
            }
        }
    }

    public List<OperationType> findAllById(List<Long> ids) {
        return operationTypeRepository.findAllById(ids);
    }

}
