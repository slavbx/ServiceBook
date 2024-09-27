package org.slavbx.servicebook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность отдельной операции, выполненной в ходе работы по обслуживанию.
 * Приобретает тип из заранее заготовленной таблицы типов операций
 * Знает, какой maintenance принадлежит
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operations", schema = "servicebook")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "type_id")
    private OperationType type;

    private String description;
}
