package com.gmail.kovalev.dao.impl;

import com.gmail.kovalev.dao.FacultyDAO;
import com.gmail.kovalev.entity.Faculty;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class FacultyDAOProxy implements InvocationHandler {
    private final FacultyDAO facultyDAO;

    public FacultyDAOProxy(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        switch (methodName) {
            case "findFacultyById" -> {
                System.out.println("Before - ищем в кэше, если там данных нет -");
                Faculty faculty = (Faculty) method.invoke(facultyDAO, args);
                System.out.println("After - сохраняем объект в кэш");
                return faculty;
            }
            case "findAllFaculties" -> {
                System.out.println("""
                        Пока тянем всё из базы.. думаю будет быстрее чем даже работа с кэш по принципу - вытянул оттуда - оставшееся из базы,
                        тем более что на статистику запросов может неверно сказаться.. будет накрутка тем кто в кэше.
                        """);
                return method.invoke(facultyDAO, args);
            }
            case "saveFaculty" -> {
                System.out.println("Before - нет");
                String message = (String) method.invoke(facultyDAO, args);
                System.out.println("After - сохраняем объект в кэш");

                String uuidString = message.substring(message.lastIndexOf(" ") + 1);
                Faculty facultyForSaveToCache = facultyDAO.findFacultyById(UUID.fromString(uuidString));
                System.out.println("Метод сохранения в кэш");

                return message;
            }
            case "updateFaculty" -> {
                System.out.println("Before - нет");
                String message = (String) method.invoke(facultyDAO, args);
                System.out.println("After - обновляем или добавляем объект в кэш/// логику здесь");

                return message;
            }
            case "deleteFacultyByUUID" -> {
                System.out.println("Before - нет");
                String message = (String) method.invoke(facultyDAO, args);
                System.out.println("After - удаляем объект из кэша");

                return message;
            }
        }
        return methodName;
    }
}
