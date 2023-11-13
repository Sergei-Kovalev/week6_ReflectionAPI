package com.gmail.kovalev.dto;

public record FacultyDTO(
        String name,
        String teacher,
        String email,
        Integer actualVisitors,
        Integer maxVisitors,
        Double pricePerDay) {
}
