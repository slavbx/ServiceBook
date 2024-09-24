package org.slavbx.servicebook.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
