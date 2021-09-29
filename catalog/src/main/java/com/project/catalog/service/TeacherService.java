package com.project.catalog.service;

import com.project.catalog.exception.BadRequestException;
import com.project.catalog.exception.NotFoundException;
import com.project.catalog.model.Subject;
import com.project.catalog.model.Teacher;
import com.project.catalog.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectService subjectService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, SubjectService subjectService) {
        this.teacherRepository = teacherRepository;
        this.subjectService = subjectService;
    }

//    public List<Teacher> getAllTeachers() {
//        return teacherRepository.findAll();
//    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);

        return teachers;
    }

    public List<Teacher> getAllPagesTeachers(Integer page, Integer size)    {
        PageRequest pageRequest = PageRequest.of(page, size);
        return teacherRepository.findAll(pageRequest).getContent();
    }

    public Teacher getTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElseThrow(() -> new NotFoundException("Teacher not found", "teacher.not.found"));
    }

    public Teacher getTeacherByCnp(String cnp) {
        Optional<Teacher> teacher = teacherRepository.findByCnp(cnp);
        return teacher.orElseThrow(() -> new NotFoundException("Teacher not found!", "teacher.not.found"));
    }

    public List<Teacher> getAllTeachersByFirstName(String name) {
        return teacherRepository.findByFirstName(name);
    }

    public List<Teacher> getAllTeachersByLastName(String name) {
        return teacherRepository.findByLastName(name);
    }

    public List<Teacher> getAllTeachersByFirstAndLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Teacher saveTeacher(Teacher teacher) {
        if (teacher.getSubjects() != null) {
            teacher.getSubjects().stream().forEach(s -> validateSubjectScoring(s));
        }
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            teacherRepository.delete(teacher.get());
        } else {
            throw new NotFoundException("Teacher not found!", "teacher.not.found");
        }
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            teacher.setId(id);
            return teacherRepository.save(teacher);
        } else {
            throw new NotFoundException("Teacher not found!", "teacher.not.found");
        }
    }

    public Teacher assignSubjectToTeacher(Long teacherId, Long subjectId) {
        Teacher teacher = getTeacher(teacherId);
        Subject subject = subjectService.getSubject(subjectId);
        teacher.getSubjects().add(subject);
        return teacherRepository.save(teacher);
    }


    private void validateSubjectScoring(Subject subject) {
        if (subject.getCoursePercent() + subject.getSeminaryPercent() != 100) {
            throw new BadRequestException("Subject scoring percent must be 100%", "subject.scoring.sum.invalid");
        }
    }

//    public TeacherDto getDemoTeacher() {
//        TeacherDto teacher = new TeacherDto();
//        teacher.setId(1L);
//        teacher.setFirstName("Ionel");
//        teacher.setLastName("Popescu");
//        teacher.setBirthDate(LocalDate.now().minusYears(35));
//        teacher.setEmploymentDate(LocalDate.now().minusYears(3));
//        teacher.setSubjects(getDemoSubjects());
//
//        return teacher;
//    }
//
//    private List<SubjectDto> getDemoSubjects() {
//        SubjectDto subject = new SubjectDto();
//        subject.setId(1L);
//        subject.setName("OOP");
//        subject.setCreditPoints(5);
//        subject.setSubjectScoring(new SubjectScoringDto(50, 50));
//        subject.setOptional(false);
//
//        return Arrays.asList(subject, subject);
//    }
}