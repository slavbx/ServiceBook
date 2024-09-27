package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.*;
import org.slavbx.servicebook.services.CarService;
import org.slavbx.servicebook.services.MaintenanceService;
import org.slavbx.servicebook.services.OperationService;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class InitController {
    private final OperationTypeService operationTypeService;
    private final OperationService operationService;
    private final MaintenanceService maintenanceService;
    private final CarService carService;

    public InitController(OperationTypeService operationTypeService, OperationService operationService,
                          MaintenanceService maintenanceService, CarService carService) {
        this.operationTypeService = operationTypeService;
        this.operationService = operationService;
        this.maintenanceService = maintenanceService;
        this.carService = carService;
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

