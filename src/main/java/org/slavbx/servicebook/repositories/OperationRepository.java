package org.slavbx.servicebook.repositories;

import org.slavbx.servicebook.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
