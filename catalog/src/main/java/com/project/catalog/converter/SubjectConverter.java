package com.project.catalog.converter;

import com.project.catalog.dto.SubjectDto;
import com.project.catalog.dto.SubjectScoringDto;
import com.project.catalog.model.Subject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectConverter {

    public SubjectDto maptoDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setIsOptional(subject.getIsOptional());
        subjectDto.setCreditPoints(subject.getCreditPoints());
        subjectDto.setSubjectScoring(new SubjectScoringDto(subject.getCoursePercent(), subject.getSeminaryPercent()));

        return subjectDto;
    }

    public List<SubjectDto> maptoDtos(List<Subject> subjects) {
        return subjects.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public Subject maptoEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setName(subjectDto.getName());
        subject.setIsOptional(subjectDto.getIsOptional());
        subject.setCreditPoints(subjectDto.getCreditPoints());

        if (subjectDto.getSubjectScoring() != null) {
            subject.setCoursePercent(subjectDto.getSubjectScoring().getCoursePercent());
            subject.setSeminaryPercent(subjectDto.getSubjectScoring().getSeminaryPercent());
        }

        return subject;
    }

    public List<Subject> mapToEntities(List<SubjectDto> subjectDtos)    {
        return subjectDtos.stream()
                .map(this::maptoEntity)
                .collect(Collectors.toList());
    }
}
