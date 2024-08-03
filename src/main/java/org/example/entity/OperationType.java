package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OPERATION_TYPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationType {

    @Id
    @Column(name = "operation_type_id")
    private Long operationTypeId;

    @Column(name = "description", nullable = false, unique = true)
    private String description;
}
