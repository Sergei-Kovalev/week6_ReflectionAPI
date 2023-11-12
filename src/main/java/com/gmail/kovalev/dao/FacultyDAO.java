package com.gmail.kovalev.dao;

import com.gmail.kovalev.entity.Faculty;

import java.util.List;
import java.util.UUID;

public interface FacultyDAO {
    Faculty findFacultyById(UUID uuid);

    List<Faculty> findAllFaculties();

    Faculty saveFaculty(Faculty faculty);

    Faculty updateFaculty(Faculty faculty);

    String deleteFacultyByUUID(UUID uuid);
}
