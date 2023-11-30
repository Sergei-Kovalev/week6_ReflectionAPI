package com.gmail.kovalev.facade;

import com.gmail.kovalev.controller.Controller;

public class AppFacade {

    private final Controller controller;

    public AppFacade() {
        this.controller = new Controller();
    }

    public void findByIdSample() {
        //*****************БЛОК ДЛЯ ПОЛУЧЕНИЯ ПО ID************************
        String facultyById = controller.findFacultyById("773dcbc0-d2fa-45b4-acf8-485b682adedd");
        System.out.println(facultyById);
        System.out.println("------------------------------------------------------");
    }

    public void findAllSample() {
        //*****************БЛОК ДЛЯ ПОЛУЧЕНИЯ ВСЕХ ИЗ БД************************
        String allFaculties = controller.findAllFaculties();
        System.out.println(allFaculties);
        System.out.println("------------------------------------------------------");
    }

    public void saveNewFromJsonSample() {
        //*****************БЛОК ДЛЯ СОХРАНЕНИЯ НОВОГО ИЗ JSON************************
        String jsonForSave = """
                {
                  "name": "Economic",
                  "teacher": "Petrov Ivan Afeevich",
                  "email": "econo-my647@yahoo.com",
                  "actualVisitors": 10,
                  "maxVisitors": 15,
                  "pricePerDay": 9.99
                }
                """;

        String report = controller.saveFaculty(jsonForSave);
        System.out.println(report);
        System.out.println("------------------------------------------------------");
    }

    public void updateFromJsonSample() {
        //*****************БЛОК ДЛЯ ОБНОВЛЕНИЯ ИЗ JSON************************
        String uuid = "773dcbc0-d2fa-45b4-acf8-485b682adedd";
        String jsonForUpdate = """
                 {
                  "name": "Culture",
                  "teacher": "Karamzina Anna Ivanovna",
                  "email": "culture@yahoo.com",
                  "actualVisitors": 20,
                  "maxVisitors": 21,
                  "pricePerDay": 9.99
                 }
                """;
        String report = controller.updateFaculty(uuid, jsonForUpdate);
        System.out.println(report);
        System.out.println("------------------------------------------------------");
    }

    public void deleteSample() {
        //*****************БЛОК ДЛЯ УДАЛЕНИЯ************************
        String s = controller.deleteFacultyByUUID("8d8cfc84-e77c-4722-b4d6-8e9fdc17c721");
        System.out.println(s);
        System.out.println("------------------------------------------------------");
    }

    public void saveFromXmlSample() {
        //*****************БЛОК ДЛЯ СОХРАНЕНИЯ НОВОГО ИЗ XML************************
        String xmlForSave = """
                <?xml version="1.0" encoding="UTF-8"?>
                 <root>
                    <name>Pagonini Day</name>
                    <teacher>Santiago Dell Torro</teacher>
                    <email>santiDT@yahoo.com</email>
                    <actualVisitors>20</actualVisitors>
                    <maxVisitors>20</maxVisitors>
                    <pricePerDay>10.99</pricePerDay>
                 </root>
                """;

        String report = controller.saveFacultyFromXML(xmlForSave);
        System.out.println(report);
        System.out.println("------------------------------------------------------");
    }

    public void rollbackDeletedFaculty() {
        String report = controller.rollbackDeletedFaculty();
        System.out.println(report);
        System.out.println("------------------------------------------------------");
    }
}
