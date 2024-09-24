package org.slavbx.servicebook.services;


import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.repositories.CarRepository;
import org.slavbx.servicebook.repositories.MaintenanceRepository;
import org.slavbx.servicebook.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor //Аннотация autowired не потребуется при создании конструктора для одного поля final
public class CarService {
    private final CarRepository carRepository;

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car getCar() {
        return this.findAll().get(0); //Машина всегда одна и существует, её достаём
    }
}
