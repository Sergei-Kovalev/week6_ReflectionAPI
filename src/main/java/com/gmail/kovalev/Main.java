package com.gmail.kovalev;

import com.gmail.kovalev.entity.Faculty;
import com.gmail.kovalev.service.FacultyService;
import com.gmail.kovalev.service.FacultyServiceImpl;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        FacultyService service = new FacultyServiceImpl();

        Faculty facultyById = service.findFacultyById(UUID.fromString("773dcbc0-d2fa-45b4-acf8-485b682adedd"));
        System.out.println(facultyById);
        System.out.println("------------------------------------------------------");

        List<Faculty> allFaculties = service.findAllFaculties();
        allFaculties.forEach(System.out::println);
        System.out.println("------------------------------------------------------");
//
//        Faculty faculty = service.saveFaculty(new Faculty("Chemistry", "Ozonov Evgeniy Dossantovich", 14, 15, 11.1));
//        System.out.println(faculty);
//        System.out.println("------------------------------------------------------");
//
//        Faculty facultyToChange = service.findFacultyById(UUID.fromString("8d8cfc84-e77c-4722-b4d6-8e9fdc17c721"));
//        facultyToChange.setName("Biology");
//        facultyToChange.setPricePerDay(6.33);
//        Faculty facultyAfterChange = service.updateFaculty(facultyToChange);
//        System.out.println(facultyAfterChange);
//        System.out.println("------------------------------------------------------");
//
//        String s = service.deleteFacultyByUUID(UUID.fromString("3af7bd12-cdb2-4f83-9ef7-c95ae58c51a5"));
//        System.out.println(s);
//        System.out.println("------------------------------------------------------");


    }
}
