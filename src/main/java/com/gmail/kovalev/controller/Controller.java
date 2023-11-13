package com.gmail.kovalev.controller;

import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.service.FacultyService;
import com.gmail.kovalev.service.impl.FacultyServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.UUID;

public class Controller {
    private final FacultyService service;

    private final Gson gson;

    public Controller() {
        this.service = new FacultyServiceImpl();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String findFacultyById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        FacultyInfoDTO facultyById = service.findFacultyById(uuid);
        return gson.toJson(facultyById);
    }

    public String findAllFaculties() {
        List<FacultyInfoDTO> allFaculties = service.findAllFaculties();
        return gson.toJson(allFaculties);
    }

    public String saveFaculty(String facultyDTOJSON) {
        FacultyDTO facultyDTO = gson.fromJson(facultyDTOJSON, FacultyDTO.class);
        String message = service.saveFaculty(facultyDTO);
        return gson.toJson(message);
    }

    public String updateFaculty(String uuidString, String facultyDTOJSON) {
        UUID uuid = UUID.fromString(uuidString);
        FacultyDTO facultyDTO = gson.fromJson(facultyDTOJSON, FacultyDTO.class);
        String message = service.updateFaculty(uuid, facultyDTO);
        return gson.toJson(message);
    }

    public String deleteFacultyByUUID(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        String message = service.deleteFacultyByUUID(uuid);
        return gson.toJson(message);
    }

}
