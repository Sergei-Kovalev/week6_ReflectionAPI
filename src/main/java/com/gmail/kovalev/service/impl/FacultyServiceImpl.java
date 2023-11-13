package com.gmail.kovalev.service.impl;

import com.gmail.kovalev.dao.FacultyDAO;
import com.gmail.kovalev.dao.impl.FacultyDAOImpl;
import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.entity.Faculty;
import com.gmail.kovalev.mapper.FacultyMapper;
import com.gmail.kovalev.mapper.impl.FacultyMapperImpl;
import com.gmail.kovalev.service.FacultyService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements FacultyService {

    private final FacultyMapper mapper;
    private final FacultyDAO facultyDAO;

    public FacultyServiceImpl() {
        this.mapper = new FacultyMapperImpl();
        this.facultyDAO = new FacultyDAOImpl();
    }

    @Override
    public FacultyInfoDTO findFacultyById(UUID uuid) {
        Faculty faculty = facultyDAO.findFacultyById(uuid);
        return mapper.fromEntityToInfoDTO(faculty);
    }

    @Override
    public List<FacultyInfoDTO> findAllFaculties() {
        return facultyDAO.findAllFaculties().stream().map(mapper::fromEntityToInfoDTO).collect(Collectors.toList());
    }

    @Override
    public String saveFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = mapper.toFaculty(facultyDTO);
        return facultyDAO.saveFaculty(faculty);
    }

    @Override
    public String updateFaculty(UUID uuid, FacultyDTO facultyDTO) {
        Faculty faculty = mapper.merge(facultyDAO.findFacultyById(uuid), facultyDTO);
        return facultyDAO.updateFaculty(faculty);
    }

    @Override
    public String deleteFacultyByUUID(UUID uuid) {
        return facultyDAO.deleteFacultyByUUID(uuid);
    }
}
