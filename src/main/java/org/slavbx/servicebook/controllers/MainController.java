package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.*;
import org.slavbx.servicebook.services.CarService;
import org.slavbx.servicebook.services.MaintenanceService;
import org.slavbx.servicebook.services.OperationService;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    private final OperationTypeService operationTypeService;
    private final OperationService operationService;
    private final MaintenanceService maintenanceService;
    private final CarService carService;

    public MainController(OperationTypeService operationTypeService, OperationService operationService,
                          MaintenanceService maintenanceService, CarService carService) {
        this.operationTypeService = operationTypeService;
        this.operationService = operationService;
        this.maintenanceService = maintenanceService;
        this.carService = carService;
    }

    @GetMapping("home")
    public String showHome(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size, Model model) { //Заполнение страницы списками сущностей и её отображение

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

    @GetMapping("/init") //Не используется
    public String init() {
        Maintenance maintenance = Maintenance.builder()
                .mileage(185000).Description("Большое обслуживание")
                .date(LocalDate.parse("2023-10-15")).build();
        maintenanceService.save(maintenance);

        OperationType type1 = OperationType.builder().name("Моторное масло").resource(10000).status("запас км").build();
        OperationType type2 = OperationType.builder().name("Масляный фильтр").resource(10000).status("запас км").build();
        OperationType type3 = OperationType.builder().name("Свечи зажигания").resource(40000).status("запас км").build();

        operationTypeService.save(type1);
        operationTypeService.save(type2);
        operationTypeService.save(type3);

        Operation operation1 = Operation.builder().maintenance(maintenance).type(type1).description("").build();
        Operation operation2 = Operation.builder().maintenance(maintenance).type(type2).description("").build();
        Operation operation3 = Operation.builder().maintenance(maintenance).type(type3).description("").build();

        operationService.save(operation1);
        operationService.save(operation2);
        operationService.save(operation3);

        type1.setOperations(List.of(operation1));
        type2.setOperations(List.of(operation2));
        type3.setOperations(List.of(operation3));

        maintenance.setOperations(List.of(operation1, operation2, operation3));

        Car car = Car.builder().mileage(85374).model("Toyota").build();
        carService.save(car);
        return "home";
    }
}
