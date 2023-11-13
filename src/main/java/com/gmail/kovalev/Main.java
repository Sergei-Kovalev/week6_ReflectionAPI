package com.gmail.kovalev;

import com.gmail.kovalev.dao.impl.FacultyDAOImpl;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.mapper.impl.FacultyMapperImpl;
import com.gmail.kovalev.service.FacultyService;
import com.gmail.kovalev.service.impl.FacultyServiceImpl;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        FacultyService service = new FacultyServiceImpl(new FacultyMapperImpl(), new FacultyDAOImpl());

        FacultyInfoDTO facultyById = service.findFacultyById(UUID.fromString("773dcbc0-d2fa-45b4-acf8-485b682adedd"));
        System.out.println(facultyById);
        System.out.println("------------------------------------------------------");

        List<FacultyInfoDTO> allFaculties = service.findAllFaculties();
        allFaculties.forEach(System.out::println);
        System.out.println("------------------------------------------------------");
//
//        String report1 = service.saveFaculty(new FacultyDTO("Chemistry", "Ozonov Evgeniy Dossantovich", 14, 15, 11.1));
//        System.out.println(report1);
//        System.out.println("------------------------------------------------------");
//
//        UUID uuid = UUID.fromString("8d8cfc84-e77c-4722-b4d6-8e9fdc17c721");
//        FacultyDTO facultyDTO = new FacultyDTO("Bio", "Kasatkin Petr Efimovich", 5, 9, 66.2);
//
//        String report2 = service.updateFaculty(uuid, facultyDTO);
//        System.out.println(report2);
//        System.out.println("------------------------------------------------------");
//
//        String s = service.deleteFacultyByUUID(UUID.fromString("829f90c8-4b90-4f54-bb6e-adddf6baa1aa"));
//        System.out.println(s);
//        System.out.println("------------------------------------------------------");


    }
}
