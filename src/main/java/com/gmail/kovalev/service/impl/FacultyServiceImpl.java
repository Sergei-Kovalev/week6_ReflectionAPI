package com.gmail.kovalev.service.impl;

import com.gmail.kovalev.dao.FacultyDAO;
import com.gmail.kovalev.dao.impl.FacultyDAOImpl;
import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.entity.Faculty;
import com.gmail.kovalev.mapper.FacultyMapper;
import com.gmail.kovalev.mapper.impl.FacultyMapperImpl;
import com.gmail.kovalev.service.FacultyService;
import com.gmail.kovalev.validator.FacultyDTOValidator;
import com.gmail.kovalev.validator.FacultyInfoDTOValidator;
import com.gmail.kovalev.validator.impl.FacultyDTOValidatorImpl;
import com.gmail.kovalev.validator.impl.FacultyInfoDTOValidatorImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements FacultyService {

    private final FacultyMapper mapper;
    private final FacultyDAO facultyDAO;
    private final FacultyDTOValidator facultyDTOValidator;
    private final FacultyInfoDTOValidator facultyInfoDTOValidator;

    public FacultyServiceImpl() {
        this.mapper = new FacultyMapperImpl();
        this.facultyDAO = new FacultyDAOImpl();
        this.facultyDTOValidator = new FacultyDTOValidatorImpl();
        this.facultyInfoDTOValidator = new FacultyInfoDTOValidatorImpl();
    }

    @Override
    public FacultyInfoDTO findFacultyById(UUID uuid) {
        Faculty faculty = facultyDAO.findFacultyById(uuid);
        FacultyInfoDTO facultyInfoDTO = mapper.fromEntityToInfoDTO(faculty);
        return facultyInfoDTOValidator.validate(facultyInfoDTO);
    }

    @Override
    public List<FacultyInfoDTO> findAllFaculties() {
        return facultyDAO.findAllFaculties().stream()
                .map(mapper::fromEntityToInfoDTO)
                .peek(facultyInfoDTOValidator::validate)
                .collect(Collectors.toList());
    }

    @Override
    public String saveFaculty(FacultyDTO facultyDTO) {
        FacultyDTO validated = facultyDTOValidator.validate(facultyDTO);
        Faculty faculty = mapper.toFaculty(validated);
        return facultyDAO.saveFaculty(faculty);
    }

    @Override
    public String updateFaculty(UUID uuid, FacultyDTO facultyDTO) {
        FacultyDTO validated = facultyDTOValidator.validate(facultyDTO);
        Faculty faculty = mapper.merge(facultyDAO.findFacultyById(uuid), validated);
        return facultyDAO.updateFaculty(faculty);
    }

    @Override
    public String deleteFacultyByUUID(UUID uuid) {
        return facultyDAO.deleteFacultyByUUID(uuid);
    }
}
