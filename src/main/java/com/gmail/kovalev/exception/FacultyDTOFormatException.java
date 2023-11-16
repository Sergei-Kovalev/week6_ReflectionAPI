package com.gmail.kovalev.exception;

/**
 * @author Sergey Kovalev
 * Объект исключения - пробрасывается если объект {@link com.gmail.kovalev.dto.FacultyDTO} не проходит валидацию
 */
public class FacultyDTOFormatException extends RuntimeException {
    public FacultyDTOFormatException(String message) {
        super(message);
    }
}
