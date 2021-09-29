package com.project.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.catalog.converter.SubjectConverter;
import com.project.catalog.dto.SubjectDto;
import com.project.catalog.dto.SubjectScoringDto;
import com.project.catalog.exception.BadRequestException;
import com.project.catalog.model.Subject;
import com.project.catalog.service.SubjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubjectController.class)
class SubjectControllerTest {

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private SubjectConverter subjectConverter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test getting all subjects")
    public void test_getAllSubjects() throws Exception {
        Subject subject = buildSubject();
        SubjectDto subjectDto = buildSubjectDto();
        List<Subject> subjects = Arrays.asList(subject);
        List<SubjectDto> subjectDtos = Arrays.asList(subjectDto);

        when(subjectService.getAllSubjects()).thenReturn(subjects);
        when(subjectConverter.maptoDtos(subjects)).thenReturn(subjectDtos);

        mockMvc.perform(get("/subject")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(subjectDto.getName()))
                .andExpect(jsonPath("$[0].creditPoints").value(subjectDto.getCreditPoints()));
    }

    @Test
    public void test_saveValidSubject() throws Exception {
        Subject subject = buildSubject();
        SubjectDto subjectDto = buildSubjectDto();
        when(subjectConverter.maptoEntity(any(SubjectDto.class))).thenReturn(subject);
        when(subjectService.saveSubject(any(Subject.class))).thenReturn(subject);
        when(subjectConverter.maptoDto(subject)).thenReturn(subjectDto);

        mockMvc.perform(post("/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }

    @Test
    public void test_saveInValidSubject() throws Exception {
        Subject subject = buildSubject();
        subject.setName("D");
        SubjectDto subjectDto = buildSubjectDto();
        subjectDto.setName("D");
        when(subjectConverter.maptoEntity(any(SubjectDto.class))).thenReturn(subject);
        when(subjectService.saveSubject(any(Subject.class))).thenReturn(subject);
        when(subjectConverter.maptoDto(subject)).thenReturn(subjectDto);

        mockMvc.perform(post("/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("name.cannot.have.this.size;"));
    }

    @Test
    public void test_saveInValidScoreSubject() throws Exception {
        Subject subject = buildSubject();
        subject.setCoursePercent(70);
        SubjectDto subjectDto = buildSubjectDto();
        when(subjectConverter.maptoEntity(any(SubjectDto.class))).thenReturn(subject);
        when(subjectService.saveSubject(any(Subject.class))).thenThrow(new BadRequestException("Subject scoring percent must be 100%", "subject.scoring.sum.invalid"));

        mockMvc.perform(post("/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Subject scoring percent must be 100%"));
    }

    @Test
    public void test_sgetSubject() throws Exception {
        Long id = 1L;
        Subject subject = buildSubject();
        SubjectDto subjectDto = buildSubjectDto();
        when(subjectService.getSubject(id)).thenReturn(subject);
        when(subjectConverter.maptoDto(subject)).thenReturn(subjectDto);

        mockMvc.perform(get("/subject/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }

    private Subject buildSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("OOP");
        subject.setCreditPoints(5);
        subject.setCoursePercent(50);
        subject.setSeminaryPercent(50);
        subject.setIsOptional(false);
        return subject;
    }

    private SubjectDto buildSubjectDto() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1L);
        subjectDto.setName("OOP");
        subjectDto.setCreditPoints(5);
        subjectDto.setSubjectScoring(new SubjectScoringDto(50, 50));
        subjectDto.setIsOptional(false);

        return subjectDto;
    }

}