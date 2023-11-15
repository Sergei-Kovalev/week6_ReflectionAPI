package com.gmail.kovalev.dao.impl;

import com.gmail.kovalev.cache.Cache;
import com.gmail.kovalev.cache.LFUCache;
import com.gmail.kovalev.cache.LRUCache;
import com.gmail.kovalev.config.Config;
import com.gmail.kovalev.dao.FacultyDAO;
import com.gmail.kovalev.entity.Faculty;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class FacultyDAOProxy implements InvocationHandler {
    private final FacultyDAO facultyDAO;
    private final Cache cache;

    public FacultyDAOProxy(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;

        int cacheCapacity = Integer.parseInt(Config.getConfig().get("application").get("collectionSize"));
        String cacheType = Config.getConfig().get("application").get("cache");

        if (cacheType.equals("LFU")) {
            this.cache = new LFUCache(cacheCapacity);
        } else if (cacheType.equals("LRU")){
            this.cache = new LRUCache(cacheCapacity);
        } else {
            this.cache = null;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        switch (methodName) {
            case "findFacultyById" -> {
                Faculty faculty = cache.get((UUID) args[0]);
                if (faculty == null) {
                    faculty = (Faculty) method.invoke(facultyDAO, args);
                    cache.set(faculty.getId(), faculty);
                }
                return faculty;
            }
            case "findAllFaculties" -> {
                System.out.println("""
                        Тянем всё из базы. Кэш не используется. Не рационально.
                        """);
                return method.invoke(facultyDAO, args);
            }
            case "saveFaculty" -> {
                String message = (String) method.invoke(facultyDAO, args);
                String uuidString = message.substring(message.lastIndexOf(" ") + 1);
                Faculty facultyForSaveToCache = facultyDAO.findFacultyById(UUID.fromString(uuidString));
                cache.set(facultyForSaveToCache.getId(), facultyForSaveToCache);
                return message;
            }
            case "updateFaculty" -> {
                String message = (String) method.invoke(facultyDAO, args);
                Faculty faculty = (Faculty) args[0];
                cache.set(faculty.getId(), faculty);
                return message;
            }
            case "deleteFacultyByUUID" -> {
                String message = (String) method.invoke(facultyDAO, args);
                cache.remove((UUID) args[0]);
                return message;
            }
        }
        return methodName;
    }
}
