package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

//    @GetMapping("/mileage")
//    public String showMileage(Model model) {
//        Car car = carService.getCar();
//        model.addAttribute("car", car);
//        return "redirect:/home";
//    }
//
//    @PostMapping("/mileage/set")
//    public String setMileage(Car carBlank) {
//        Car car = carService.getCar();
//        car.setMileage(carBlank.getMileage());
//        carService.save(car);
//        return "redirect:/home";
//    }

}
