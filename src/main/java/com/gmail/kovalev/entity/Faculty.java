package com.gmail.kovalev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Faculty {

    private UUID id;
    private String name;
    private String teacher;
    private Integer actualVisitors;
    private Integer maxVisitors;
    private Double pricePerDay;

}
