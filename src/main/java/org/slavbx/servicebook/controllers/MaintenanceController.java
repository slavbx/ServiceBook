package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.Car;
import org.slavbx.servicebook.models.Maintenance;
import org.slavbx.servicebook.models.MaintenanceDTO;
import org.slavbx.servicebook.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    //add и delete

    @PostMapping("/maintenances/add")
    public String addMaintenance(@ModelAttribute(value = "maintenanceDTO") MaintenanceDTO maintenanceDTO) {
        maintenanceService.createMaintenance(maintenanceDTO);
        return "redirect:/home";
    }

}
