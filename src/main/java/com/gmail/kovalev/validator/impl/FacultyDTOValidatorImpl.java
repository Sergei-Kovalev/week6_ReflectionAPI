package com.gmail.kovalev.validator.impl;

import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.exception.FacultyDTOFormatException;
import com.gmail.kovalev.validator.FacultyDTOValidator;

public class FacultyDTOValidatorImpl implements FacultyDTOValidator {

    public FacultyDTO validate(FacultyDTO facultyDTO) {
        checkName(facultyDTO.name());
        checkTeacher(facultyDTO.teacher());
        checkEmail(facultyDTO.email());
        checkActualVisitors(facultyDTO.actualVisitors(), facultyDTO.maxVisitors());
        checkMaxVisitors(facultyDTO.maxVisitors());
        checkPricePerDay(facultyDTO.pricePerDay());
        return facultyDTO;
    }


    private static void checkName(String name) throws FacultyDTOFormatException {
        if (!name.matches("[a-zA-Zа-яёА-ЯЁ\\s]{1,30}")) {
            throw new FacultyDTOFormatException(
                    "Название факультета (не может быть null или пустым, содержит 1-30 символов(на русском или английском, включая пробелы))"
            );
        }
    }

    private static void checkTeacher(String teacher) throws FacultyDTOFormatException {
        if (!teacher.matches("[a-zA-Zа-яёА-ЯЁ\\s]{1,50}")) {
            throw new FacultyDTOFormatException(
                    "ФИО преподавателя (не может быть null или пустым, содержит 1-50 символов(на русском или английском, включая пробелы))"
            );
        }
    }

    private static void checkEmail(String email) {
        if (!email.matches("^[\\w_-]+@[\\w_-]+\\.[a-z]{2,}$")) {
            throw new FacultyDTOFormatException(
                    "e-mail не прошёл валидацию ... убедитесь что вы ввели его верно!"
            );
        }
    }

    private static void checkActualVisitors(Integer actualVisitors, Integer maxVisitors) throws FacultyDTOFormatException {
        if (actualVisitors == null) {
            throw new FacultyDTOFormatException(
                    "Количество посетителей не может быть null"
            );
        }
        if (actualVisitors < 0) {
            throw new FacultyDTOFormatException(
                    "Количество посетителей не может быть меньше нуля"
            );
        }
        if (actualVisitors > maxVisitors) {
            throw new FacultyDTOFormatException(
                    "Количество посетителей не может быть больше, чем вмещает группа"
            );
        }
    }

    private static void checkMaxVisitors(Integer maxVisitors) throws FacultyDTOFormatException {
        if (maxVisitors == null) {
            throw new FacultyDTOFormatException(
                    "Количество посетителей не может быть null"
            );
        }
        if (maxVisitors < 0) {
            throw new FacultyDTOFormatException(
                    "Количество посетителей не может быть меньше нуля"
            );
        }
        if (maxVisitors > 50) {
            throw new FacultyDTOFormatException(
                    "Максимальная аудитория вмещает 50 абитуриентов. Группы ограничены этим числом"
            );
        }
    }

    private static void checkPricePerDay(Double pricePerDay) throws FacultyDTOFormatException {
        if (pricePerDay == null) {
            throw new FacultyDTOFormatException(
                    "Цена посещения не может быть null"
            );
        }
        if (pricePerDay < 2.0) {
            throw new FacultyDTOFormatException(
                    "Минимальная цена для любого факультета 2,0 руб./посещение"
            );
        }
        if (pricePerDay >= 100.0) {
            throw new FacultyDTOFormatException(
                    "Максимальная цена для любого факультета 100,0 руб./посещение. Выше - придётся делиться"
            );
        }
    }
}
