package org.slavbx.servicebook.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Сущность типа операции, заранее заготовлен в таблице, знает о том, какие операции соответствуют ему.
 * Имеет ресурс по пробегу, по которому вычисляется статус типа операции для того, чтобы
 * пользователь мог контролировать процесс обслуживания
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "operation_types", schema = "servicebook")
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL) //чтобы можно было удалить все Operations при удалении Type
    private List<Operation> operations;

    private String name;
    private Integer resource;
    private String status;
}
