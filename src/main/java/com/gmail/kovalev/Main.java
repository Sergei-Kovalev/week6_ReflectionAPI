package com.gmail.kovalev;

import com.gmail.kovalev.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
//
//        String facultyById = controller.findFacultyById("773dcbc0-d2fa-45b4-acf8-485b682adedd");
//        System.out.println(facultyById);
//        System.out.println("------------------------------------------------------");

        String allFaculties = controller.findAllFaculties();
        System.out.println(allFaculties);
        System.out.println("------------------------------------------------------");


//        String jsonForSave = """
//                {
//                  "name": "Economic",
//                  "teacher": "Petrov Ivan Afeevich",
//                  "email": "econo-my647@yahoo.com",
//                  "actualVisitors": 10,
//                  "maxVisitors": 15,
//                  "pricePerDay": 9.99
//                }
//                """;
//
//        String report1 = controller.saveFaculty(jsonForSave);
//        System.out.println(report1);
//        System.out.println("------------------------------------------------------");

//        String uuid = "f9a06343-3187-4f0d-a7b5-d56dd608f261";
//        String jsonForUpdate = """
//                 {
//                  "name": "Culture",
//                  "teacher": "Karamzina Anna Ivanovna",
//                  "email": "culture@yahoo.com",
//                  "actualVisitors": 20,
//                  "maxVisitors": 21,
//                  "pricePerDay": 9.99
//                 }
//                """;
//
//        String report2 = controller.updateFaculty(uuid, jsonForUpdate);
//        System.out.println(report2);
//        System.out.println("------------------------------------------------------");

//        String s = controller.deleteFacultyByUUID("f9a06343-3187-4f0d-a7b5-d56dd608f261");
//        System.out.println(s);
//        System.out.println("------------------------------------------------------");


    }
}
