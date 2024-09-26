package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.MaintenanceDTO;
import org.slavbx.servicebook.services.MaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MaintenanceController {
    final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

//    @PostMapping("/maintenance/add")
//    public String addMaintenance(@ModelAttribute(value = "maintenanceDTO") MaintenanceDTO maintenanceDTO) {
//        maintenanceService.createMaintenance(maintenanceDTO);
//        return "redirect:/home";
//    }
//
//    @GetMapping("/maintenance/delete/{id}")
//    public String deleteMaintenance(@PathVariable(value = "id") Long id) {
//        maintenanceService.deleteById(id);
//        return "redirect:/home";
//    }

}
