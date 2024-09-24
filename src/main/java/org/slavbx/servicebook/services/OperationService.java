package org.slavbx.servicebook.services;

import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //Аннотация autowired не потребуется при создании конструктора для одного поля final
public class OperationService {
    private final OperationRepository operationRepository;

    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }
}
