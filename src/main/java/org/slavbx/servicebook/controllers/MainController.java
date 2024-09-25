package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.*;
import org.slavbx.servicebook.services.CarService;
import org.slavbx.servicebook.services.MaintenanceService;
import org.slavbx.servicebook.services.OperationService;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    OperationTypeService operationTypeService;

    @Autowired
    OperationService operationService;

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    CarService carService;

    @GetMapping("home")
    public String showHome(Model model) { //Заполнение страницы списками сущностей и её отображение

        //Таблица Текущий статус обслуживания
        operationTypeService.refreshAllTypeStatus();
        OperationType operationType = OperationType.builder().build();
        model.addAttribute("operationType", operationType); //пустая болванка для HTML-страницы
        model.addAttribute("operationTypes", operationTypeService.findAll()); //лист, который в цикле разворачивает thymeleaf

        //Форма Добавления работ по обслуживанию
        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
        model.addAttribute("maintenanceDTO", maintenanceDTO);

        //Таблица История сеансов обслуживания
        model.addAttribute("maintenances", maintenanceService.findAll());
        //model.addAttribute("operationTypes", operationTypeService.findAll()); //существует выше

        //Отображение текущего пробега автомобиля
        Car car = carService.getCar();
        model.addAttribute("car", car);
        return "home";
    }

    @GetMapping("/init") //Не используется
    public String init() {
        Maintenance maintenance = Maintenance.builder()
                .mileage(185000).Description("Большое обслуживание")
                .date(LocalDate.parse("2023-10-15")).build();
        maintenanceService.save(maintenance);

        OperationType type1 = OperationType.builder().name("Замена масла в двигателе").resource(10000).status("просрочено").build();
        OperationType type2 = OperationType.builder().name("Замена масла в АКПП").resource(80000).status("просрочено").build();
        OperationType type3 = OperationType.builder().name("Замена масла воздушного фильтра").resource(10000).status("просрочено").build();

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

        Car car = Car.builder().mileage(85374).model("Toyota Caldina").build();
        carService.save(car);

        return "home";
    }
}
