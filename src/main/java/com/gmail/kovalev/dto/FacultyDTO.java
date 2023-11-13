package com.gmail.kovalev.dto;

public record FacultyDTO(
        String name,
        String teacher,
        Integer actualVisitors,
        Integer maxVisitors,
        Double pricePerDay) {
}
