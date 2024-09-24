package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.Operation;
import org.slavbx.servicebook.models.OperationType;
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

    @GetMapping("home")
    public String showHome(Model model) { //Заполнение страницы списками сущностей и её отображение
        operationTypeService.refreshAllTypeStatus();
        OperationType operationType = OperationType.builder().build();
        model.addAttribute("operationType", operationType); //пустая болванка для HTML-страницы
        model.addAttribute("operationTypes", operationTypeService.findAll()); //лист, который в цикле разворачивает thymeleaf

        Maintenance maintenance = Maintenance.builder().build();
        model.addAttribute("maintenance", maintenance);
        model.addAttribute("maintenances", maintenanceService.findAll());
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

        Operation operation1 = Operation.builder().maintenance(maintenance).type(type1).Description("").build();
        Operation operation2 = Operation.builder().maintenance(maintenance).type(type2).Description("").build();
        Operation operation3 = Operation.builder().maintenance(maintenance).type(type3).Description("").build();

        operationService.save(operation1);
        operationService.save(operation2);
        operationService.save(operation3);

        type1.setOperations(List.of(operation1));
        type2.setOperations(List.of(operation2));
        type3.setOperations(List.of(operation3));

        maintenance.setOperations(List.of(operation1, operation2, operation3));

        return "home";
    }
}
