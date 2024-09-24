package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.models.OperationType;
import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller("/operation-types")
public class OperationTypeController {
    @Autowired
    OperationTypeService operationTypeService;

    //add и delete

//    @PostMapping("/add")
//    public String addOperationType(@ModelAttribute(value = "meterType") OperationType operationType) {
//        operationTypeService.save(operationType);
//        return "redirect:/home";
//    }

}

