package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaxSalaryWorker {
    private String name;
    private int salary;
}
