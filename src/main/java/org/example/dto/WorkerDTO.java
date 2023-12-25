package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.enums.Level;

import java.time.LocalDate;

@Data
@Builder
public class WorkerDTO {
    private String name;
    private LocalDate birthday;
    private Level level;
    private int salary;
}
