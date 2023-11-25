package com.gmail.kovalev;

import com.gmail.kovalev.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        //*****************БЛОК ДЛЯ ПОЛУЧЕНИЯ ПО ID************************
        String facultyById = controller.findFacultyById("773dcbc0-d2fa-45b4-acf8-485b682adedd");
        System.out.println(facultyById);
        System.out.println("------------------------------------------------------");

//
//        //*****************БЛОК ДЛЯ ПОЛУЧЕНИЯ ВСЕХ ИЗ БД************************
//        String allFaculties = controller.findAllFaculties();
//        System.out.println(allFaculties);
//        System.out.println("------------------------------------------------------");

//        //*****************БЛОК ДЛЯ СОХРАНЕНИЯ НОВОГО ИЗ JSON************************
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

//        //*****************БЛОК ДЛЯ ОБНОВЛЕНИЯ ИЗ JSON************************
//        String uuid = "773dcbc0-d2fa-45b4-acf8-485b682adedd";
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
//        String report2 = controller.updateFaculty(uuid, jsonForUpdate);
//        System.out.println(report2);
//        System.out.println("------------------------------------------------------");

//        //*****************БЛОК ДЛЯ УДАЛЕНИЯ************************
//        String s = controller.deleteFacultyByUUID("8d8cfc84-e77c-4722-b4d6-8e9fdc17c721");
//        System.out.println(s);
//        System.out.println("------------------------------------------------------");
//
//
//        //*****************БЛОК ДЛЯ СОХРАНЕНИЯ НОВОГО ИЗ XML************************
//        String xmlForSave = """
//                <?xml version="1.0" encoding="UTF-8"?>
//                 <root>
//                    <name>Pagonini Day</name>
//                    <teacher>Santiago Dell Torro</teacher>
//                    <email>santiDT@yahoo.com</email>
//                    <actualVisitors>20</actualVisitors>
//                    <maxVisitors>20</maxVisitors>
//                    <pricePerDay>10.99</pricePerDay>
//                 </root>
//                """;
//
//        String report1 = controller.saveFacultyFromXML(xmlForSave);
//        System.out.println(report1);
//        System.out.println("------------------------------------------------------");
    }
}
