package com.gmail.kovalev.service;

import com.gmail.kovalev.dao.FacultyDAO;
import com.gmail.kovalev.dao.FacultyDAOImpl;
import com.gmail.kovalev.entity.Faculty;

import java.util.List;
import java.util.UUID;

public class FacultyServiceImpl implements FacultyService {

    FacultyDAO facultyDAO;

    public FacultyServiceImpl() {
        this.facultyDAO = new FacultyDAOImpl();
    }

    @Override
    public Faculty findFacultyById(UUID uuid) {
        return facultyDAO.findFacultyById(uuid);
    }

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyDAO.findAllFaculties();
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyDAO.saveFaculty(faculty);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyDAO.updateFaculty(faculty);
    }

    @Override
    public String deleteFacultyByUUID(UUID uuid) {
        return facultyDAO.deleteFacultyByUUID(uuid);
    }
}
