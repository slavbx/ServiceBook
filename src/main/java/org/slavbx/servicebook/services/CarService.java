package org.slavbx.servicebook.services;


import lombok.RequiredArgsConstructor;
import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.repositories.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис для работы с сущностью Car
 */
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
