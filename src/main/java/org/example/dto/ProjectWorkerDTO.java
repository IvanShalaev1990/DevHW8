package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectWorkerDTO {
    private int projectID;
    private int workerID;
}
