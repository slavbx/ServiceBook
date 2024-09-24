package org.slavbx.servicebook.repositories;

import org.slavbx.servicebook.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
