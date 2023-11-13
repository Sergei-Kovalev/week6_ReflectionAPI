package com.gmail.kovalev.exception;

public class FacultyNotFoundException extends RuntimeException {
    public FacultyNotFoundException(String id) {
        super(String.format("Faculty with id: %s not found", id));
    }
}
