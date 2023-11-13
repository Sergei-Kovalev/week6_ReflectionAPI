package com.gmail.kovalev.validator.impl;

import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.exception.FacultyInfoDTOFormatException;
import com.gmail.kovalev.validator.FacultyInfoDTOValidator;

import java.util.UUID;

public class FacultyInfoDTOValidatorImpl implements FacultyInfoDTOValidator {
    @Override
    public FacultyInfoDTO validate(FacultyInfoDTO facultyInfoDTO) {
        checkUuid(facultyInfoDTO.id());
        checkName(facultyInfoDTO.name());
        checkTeacher(facultyInfoDTO.teacher());
        checkEmail(facultyInfoDTO.email());
        checkFreePlaces(facultyInfoDTO.freePlaces());
        checkPricePerDay(facultyInfoDTO.pricePerDay());
        return facultyInfoDTO;
    }

    private static void checkUuid(UUID id) throws FacultyInfoDTOFormatException {
        if (id == null) {
            throw new FacultyInfoDTOFormatException(
                    "UUID приходящий из базы данных не может быть null."
            );
        }
    }

    private static void checkName(String name) throws FacultyInfoDTOFormatException {
        if (!name.matches("[a-zA-Zа-яёА-ЯЁ\\s]{1,30}")) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение facultyName (не может быть null или пустым, содержит 1-30 символов(на русском или английском, включая пробелы))"
            );
        }
    }

    private static void checkTeacher(String teacher) throws FacultyInfoDTOFormatException {
        if (!teacher.matches("[a-zA-Zа-яёА-ЯЁ\\s]{1,50}")) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение ФИО преподавателя (не может быть null или пустым, содержит 1-50 символов(на русском или английском, включая пробелы))"
            );
        }
    }

    private static void checkEmail(String email) throws FacultyInfoDTOFormatException {
        if (!email.matches("^[\\w_-]+@[\\w_-]+\\.[a-z]{2,}$")) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение e-mail.. не прошло валидацию!"
            );
        }
    }

    private static void checkFreePlaces(Integer freePlaces) throws FacultyInfoDTOFormatException {
        if (freePlaces < 0) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное количество свободных мест.. не может быть отрицательным!"
            );
        }
    }

    private void checkPricePerDay(Double pricePerDay) throws FacultyInfoDTOFormatException {
        if (pricePerDay == null) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение цены посещения - не может быть null"
            );
        }
        if (pricePerDay < 2.0) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение цены посещения - Минимальная цена для любого факультета 2,0 руб./посещение"
            );
        }
        if (pricePerDay >= 100.0) {
            throw new FacultyInfoDTOFormatException(
                    "Из базы пришло неверное значение цены посещения - Максимальная цена для любого факультета 100,0 руб./посещение. Выше - придётся делиться"
            );
        }
    }
}
