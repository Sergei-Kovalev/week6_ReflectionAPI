package com.gmail.kovalev.dto;

import java.util.UUID;

public record FacultyInfoDTO(
        UUID id,
        String name,
        String teacher,
        String email,
        Integer freePlaces,
        Double pricePerDay) {
}
