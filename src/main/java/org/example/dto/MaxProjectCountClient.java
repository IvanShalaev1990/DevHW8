package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaxProjectCountClient {
    private String name;
    private int projectCount;
}
