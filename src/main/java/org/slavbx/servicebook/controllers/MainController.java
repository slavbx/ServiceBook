package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.*;
import org.slavbx.servicebook.services.CarService;
import org.slavbx.servicebook.services.MaintenanceService;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    /**
     * Метод отображает всё содержимое страницы
     * @param page номер текущей страницы Истории работ по обслуживанию
     * @param size размер страницы Истории работ по обслуживанию
     * @return стартовую страницу
    */
    @GetMapping("/home")
    public String showHome(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size, Model model) {
        //Отображение таблицы Текущий статус обслуживания
        operationTypeService.refreshAllTypeStatus();
        OperationType operationType = OperationType.builder().build();
        model.addAttribute("operationType", operationType); //пустая болванка для HTML-страницы
        model.addAttribute("operationTypes", operationTypeService.findAll()); //лист, который в цикле разворачивает thymeleaf

        //Форма Добавления работ по обслуживанию
        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
        maintenanceDTO.setDate(LocalDate.now()); //Инициализация пользовательского ввода
        maintenanceDTO.setMileage(carService.getCar().getMileage()); //Инициализация пользовательского ввода
        model.addAttribute("maintenanceDTO", maintenanceDTO);

        //Отображение таблицы История работ по обслуживанию
        Page<Maintenance> maintenancePage = maintenanceService.findAll(page, size);
        model.addAttribute("maintenancePage", maintenancePage);

        //Отображение текущего пробега автомобиля
        Car car = carService.getCar();
        model.addAttribute("car", car);
        return "home";
    }

    /**
     * Метод для установки нового значения текущего пробега нашего автомобиля
     * @param carBlank пустая заготовка для передачи пробега из view в сервис
     * @return перенаправляет на обновлённую страницу
     */
    @PostMapping("/cars/mileage/set")
    public String setMileage(Car carBlank) {
        Car car = carService.getCar();
        car.setMileage(carBlank.getMileage());
        carService.save(car);
        return "redirect:/home";
    }

    /**
     * Метод создаёт новую работу по обслуживанию по введенным на форме данным
     * @param maintenanceDTO для возможности создания maintenance по выбранным типам operations на форме
     * @return перенаправляет на обновлённую страницу
     */
    @PostMapping("/maintenance/add")
    public String addMaintenance(@ModelAttribute(value = "maintenanceDTO") MaintenanceDTO maintenanceDTO) {
        maintenanceService.createMaintenance(maintenanceDTO);
        return "redirect:/home";
    }

    /**
     * Метод позволяет удалить работу по обслуживанию из истории работ
     * @param id идентификатор записи текущей maintenance
     * @return перенаправляет на обновлённую страницу
     */
    @GetMapping("/maintenance/delete/{id}")
    public String deleteMaintenance(@PathVariable(value = "id") Long id) {
        maintenanceService.deleteById(id);
        return "redirect:/home";
    }
}
