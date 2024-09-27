package org.slavbx.servicebook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Сущность работы по обслуживанию, которая хранит список операций,
 * дату и пробег, при которых была выполнена эта работа
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenances", schema = "servicebook")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL)
    private List<Operation> operations;
    private Integer mileage;
    private LocalDate date;
    private String Description;
}
