package org.slavbx.servicebook.repositories;

import org.slavbx.servicebook.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
