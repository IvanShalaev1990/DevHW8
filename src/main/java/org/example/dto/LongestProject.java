package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LongestProject {
    private int name;
    private int monthCount;
}
