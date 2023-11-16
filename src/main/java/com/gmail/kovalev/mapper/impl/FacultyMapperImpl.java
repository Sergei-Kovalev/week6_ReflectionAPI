package com.gmail.kovalev.mapper.impl;

import com.gmail.kovalev.dto.FacultyDTO;
import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.entity.Faculty;
import com.gmail.kovalev.mapper.FacultyMapper;

/**
 * @author Sergey Kovalev
 * реализация интерфейсов и его методов:
 * @see FacultyMapper
 */
public class FacultyMapperImpl implements FacultyMapper {

    /**
     * Метод для преобразования объекта
     * @param facultyDTO - объект для преобразования {@link FacultyDTO}
     * @return объект класса {@link Faculty}
     */
    @Override
    public Faculty toFaculty(FacultyDTO facultyDTO) {
        return FacultyMapper.INSTANCE.toFaculty(facultyDTO);
    }

    /**
     * Метод для преобразования объекта
     * @param faculty - объект для преобразования {@link Faculty}
     * @return объект класса {@link FacultyInfoDTO}
     */
    @Override
    public FacultyInfoDTO fromEntityToInfoDTO(Faculty faculty) {
        return FacultyMapper.INSTANCE.fromEntityToInfoDTO(faculty);
    }

    /**
     * Метод для слияния объектов
     * @param faculty - объект для преобразования {@link Faculty}
     * @param facultyDTO - объект для преобразования {@link FacultyDTO}
     * @return объект класса {@link Faculty} с данными из {@link FacultyDTO}
     */
    @Override
    public Faculty merge(Faculty faculty, FacultyDTO facultyDTO) {
        return FacultyMapper.INSTANCE.merge(faculty, facultyDTO);
    }
}
