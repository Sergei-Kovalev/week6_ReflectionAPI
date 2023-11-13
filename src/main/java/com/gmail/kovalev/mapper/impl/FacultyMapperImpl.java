package com.gmail.kovalev.mapper.impl;

import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.entity.Faculty;
import com.gmail.kovalev.mapper.FacultyMapper;

public class FacultyMapperImpl implements FacultyMapper {
    @Override
    public Faculty toFaculty(FacultyDTO facultyDTO) {
        return FacultyMapper.INSTANCE.toFaculty(facultyDTO);
    }

    @Override
    public FacultyInfoDTO fromEntityToInfoDTO(Faculty faculty) {
        return FacultyMapper.INSTANCE.fromEntityToInfoDTO(faculty);
    }

    @Override
    public Faculty merge(Faculty faculty, FacultyDTO facultyDTO) {
        return FacultyMapper.INSTANCE.merge(faculty, facultyDTO);
    }
}
