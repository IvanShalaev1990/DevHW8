package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ProjectDTO {
    private int clientID;
    private LocalDate startDate;
    private LocalDate finishDate;
}
