package org.slavbx.servicebook.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO сущности maintenance, добавлен для того, чтобы при создании из view новой maintenance,
 * у нее добавились operations с типами которые пользователь выберет в выпадающем списке
 */
@Getter
@Setter
public class MaintenanceDTO {
    private Integer mileage;
    private LocalDate date;
    private List<Long> selectedOperationTypeIds; // Ids выбранных типов операций
 }
