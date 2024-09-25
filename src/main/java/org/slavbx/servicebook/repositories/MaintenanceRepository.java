package org.slavbx.servicebook.repositories;

import org.slavbx.servicebook.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface MaintenanceRepository extends PagingAndSortingRepository<Maintenance, Long> {
    Collection<Maintenance> findAll();

    void save(Maintenance maintenance);

    void deleteById(Long id);
}
