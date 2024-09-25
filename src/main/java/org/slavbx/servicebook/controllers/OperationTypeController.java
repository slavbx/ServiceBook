package org.slavbx.servicebook.controllers;

import org.slavbx.servicebook.services.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller("/operation-types")
public class OperationTypeController {
    final
    OperationTypeService operationTypeService;

    public OperationTypeController(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }

    //add и delete

}

