package com.gmail.kovalev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Faculty {

    private UUID id;
    private String name;
    private String teacher;
    private Integer actualVisitors;
    private Integer maxVisitors;
    private Double pricePerDay;

    public Faculty(String name, String teacher, Integer actualVisitors, Integer maxVisitors, Double pricePerDay) {
        this.name = name;
        this.teacher = teacher;
        this.actualVisitors = actualVisitors;
        this.maxVisitors = maxVisitors;
        this.pricePerDay = pricePerDay;
    }
}
