package com.gmail.kovalev.exception;

/**
 * @author Sergey Kovalev
 * Объект исключения - пробрасывается если объект {@link com.gmail.kovalev.dto.FacultyInfoDTO} не проходит валидацию
 */
public class FacultyInfoDTOFormatException extends RuntimeException {
    public FacultyInfoDTOFormatException(String message) {
        super(message);
    }
}
