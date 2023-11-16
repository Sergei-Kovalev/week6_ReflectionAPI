package com.gmail.kovalev.exception;

/**
 * @author Sergey Kovalev
 * Объект исключения - пробрасывается если такой факультатив не найден в БД
 */
public class FacultyNotFoundException extends RuntimeException {
    public FacultyNotFoundException(String id) {
        super(String.format("Faculty with id: %s not found", id));
    }
}
