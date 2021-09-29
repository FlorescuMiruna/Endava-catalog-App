package com.project.catalog.converter;

import com.project.catalog.dto.TeacherDto;
import com.project.catalog.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherConverter {

    private final SubjectConverter subjectConverter;

    @Autowired
    public TeacherConverter(SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    public List<TeacherDto> maptoDtos(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public TeacherDto maptoDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setCnp(teacher.getCnp());
        teacherDto.setSalary(teacher.getSalary());
        teacherDto.setEmploymentDate(teacher.getEmploymentDate());
        teacherDto.setBirthDate(teacher.getBirthDate());
        if (teacher.getSubjects() != null) {
            teacherDto.setSubjects(subjectConverter.maptoDtos(teacher.getSubjects()));
        }

        return teacherDto;
    }

    public Teacher maptoEntity(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setBirthDate(dto.getBirthDate());
        teacher.setEmploymentDate(dto.getEmploymentDate());
        teacher.setCnp(dto.getCnp());
        teacher.setSalary(dto.getSalary());
        if (dto.getSubjects() != null) {
            teacher.setSubjects(subjectConverter.mapToEntities(dto.getSubjects()));
        }

        return teacher;
    }
}
