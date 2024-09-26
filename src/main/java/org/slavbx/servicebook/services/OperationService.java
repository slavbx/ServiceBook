package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .max((o1, o2) -> o2.getMaintenance().getMileage() - o1.getMaintenance().getMileage());
    }
}
