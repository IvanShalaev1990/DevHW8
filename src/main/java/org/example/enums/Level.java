package org.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {
TRAINEE("Trainee"),JUNIOR("Junior"),MIDDLE("Middle"),SENIOR("Senior");
    private final String level;

}
