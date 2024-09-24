package org.slavbx.servicebook.repositories;

import org.slavbx.servicebook.models.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
