package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с сущностью Operation
 */
@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository; //Autowired не потребуется для одного поля final

    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public Optional<Operation> getOperationOfMaxMileageByType(OperationType operationType) {
        return this.findAll().stream()
                .filter(op -> op.getType().equals(operationType))
                .max(Comparator.comparingInt(o -> o.getMaintenance().getMileage()));
    }
}
