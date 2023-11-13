package com.gmail.kovalev;

import com.gmail.kovalev.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        String facultyById = controller.findFacultyById("773dcbc0-d2fa-45b4-acf8-485b682adedd");
        System.out.println(facultyById);
        System.out.println("------------------------------------------------------");

        String allFaculties = controller.findAllFaculties();
        System.out.println(allFaculties);
        System.out.println("------------------------------------------------------");


//        String jsonForSave = """
//                {
//                  "name": "NanoTech",
//                  "teacher": "Petrov Ivan Afeevich",
//                  "actualVisitors": 11,
//                  "maxVisitors": 20,
//                  "pricePerDay": 6.72
//                }
//                """;
//
//        String report1 = controller.saveFaculty(jsonForSave);
//        System.out.println(report1);
//        System.out.println("------------------------------------------------------");

//        String uuid = "197caaa5-25ac-4daf-a395-6d28f98b8b4b";
//        String jsonForUpdate = """
//                 {
//                  "name": "Culture",
//                  "teacher": "Karamzina Anna Ivanovna",
//                  "actualVisitors": 20,
//                  "maxVisitors": 21,
//                  "pricePerDay": 9.99
//                 }
//                """;
//
//        String report2 = controller.updateFaculty(uuid, jsonForUpdate);
//        System.out.println(report2);
//        System.out.println("------------------------------------------------------");
//
//        String s = controller.deleteFacultyByUUID("c118621b-4e46-41b4-a872-a82eb21504e2");
//        System.out.println(s);
//        System.out.println("------------------------------------------------------");


    }
}
