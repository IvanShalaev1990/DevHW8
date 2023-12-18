package org.example.dto;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Data
@Builder
public class YoungestEldestWorker {
        private String type;
        private String name;
        private LocalDate birthday;
}
