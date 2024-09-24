package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    @Autowired
    CarService carService;

//    @GetMapping
//    public String showMileage(Model model) {
//        Car car = carService.getCar();
//        model.addAttribute("car", car);
//        return "home";
//    }

    @PostMapping("/cars")
    public String setMileage(Car carBlank) {
        Car car = carService.getCar();
        car.setMileage(carBlank.getMileage());
        carService.save(car);
        return "redirect:/home";
    }

}
