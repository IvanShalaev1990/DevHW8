package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectPrices {
    private int name;
    private int price;
}
