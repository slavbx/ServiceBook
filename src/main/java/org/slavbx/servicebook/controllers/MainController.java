package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.*;
import org.slavbx.servicebook.services.CarService;
import org.slavbx.servicebook.services.MaintenanceService;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private final OperationTypeService operationTypeService;
    private final MaintenanceService maintenanceService;
    private final CarService carService;

    public MainController(OperationTypeService operationTypeService, MaintenanceService maintenanceService, CarService carService) {
        this.operationTypeService = operationTypeService;
        this.maintenanceService = maintenanceService;
        this.carService = carService;
    }

    @GetMapping("home")
    public String showHome(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size, Model model) {
        //Таблица Текущий статус обслуживания
        operationTypeService.refreshAllTypeStatus();
        OperationType operationType = OperationType.builder().build();
        model.addAttribute("operationType", operationType); //пустая болванка для HTML-страницы
        model.addAttribute("operationTypes", operationTypeService.findAll()); //лист, который в цикле разворачивает thymeleaf

        //Форма Добавления работ по обслуживанию
        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
        model.addAttribute("maintenanceDTO", maintenanceDTO);

        //Таблица История работ по обслуживанию
        Page<Maintenance> maintenancePage = maintenanceService.findAll(page, size);
        model.addAttribute("maintenancePage", maintenancePage);

        //Отображение текущего пробега автомобиля
        Car car = carService.getCar();
        model.addAttribute("car", car);
        return "home";
    }

    @GetMapping("/cars/mileage")
    public String showMileage(Model model) {
        Car car = carService.getCar();
        model.addAttribute("car", car);
        return "redirect:/home";
    }

    @PostMapping("/cars/mileage/set")
    public String setMileage(Car carBlank) {
        Car car = carService.getCar();
        car.setMileage(carBlank.getMileage());
        carService.save(car);
        return "redirect:/home";
    }

    @PostMapping("/maintenance/add")
    public String addMaintenance(@ModelAttribute(value = "maintenanceDTO") MaintenanceDTO maintenanceDTO) {
        maintenanceService.createMaintenance(maintenanceDTO);
        return "redirect:/home";
    }

    @GetMapping("/maintenance/delete/{id}")
    public String deleteMaintenance(@PathVariable(value = "id") Long id) {
        maintenanceService.deleteById(id);
        return "redirect:/home";
    }
}
