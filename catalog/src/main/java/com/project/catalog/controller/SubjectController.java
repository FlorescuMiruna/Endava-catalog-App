package com.project.catalog.controller;

import com.project.catalog.converter.SubjectConverter;
import com.project.catalog.dto.SubjectDto;
import com.project.catalog.model.Subject;
import com.project.catalog.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectConverter subjectConverter;

    private Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectConverter subjectConverter) {
        this.subjectService = subjectService;
        this.subjectConverter = subjectConverter;
    }

    @GetMapping(value = "")
    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();

        return subjectConverter.maptoDtos(subjects);
    }

    /*
       @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.
     */
    @GetMapping(value = "/{id}")
    public SubjectDto getSubject(@PathVariable("id") Long id) {
        Subject subject = subjectService.getSubject(id);

        return subjectConverter.maptoDto(subject);
    }

//    @GetMapping(value = "/demo")
//    public Subject getParamSubject(@RequestParam("id") Long id) {
//        return subjectService.getSubject(id);
//    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Subject> getSubject(@PathVariable("id") Long id) {
//        try {
//            Subject subject = subjectService.getSubject(id);
//            return ResponseEntity.ok(subject);
//        } catch (RuntimeException e)    {
//            return ResponseEntity.notFound().build();
//        }
//    }

    /*
    @RequestBody annotation maps the HttpRequest body to a transfer or domain object,
    enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
    Spring automatically deserializes the JSON into a Java type, assuming an appropriate one is specified.
     */
    @PostMapping("")
    public SubjectDto saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectConverter.maptoEntity(subjectDto);
        subject = subjectService.saveSubject(subject);

        logger.info("Saved new subject {}", subject);
        return subjectConverter.maptoDto(subject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        logger.info("Deleted subject with {}", id);
        subjectService.deleteSubject(id);
    }

    @PutMapping("/{id}")
    public SubjectDto updateSubject(@PathVariable Long id, @RequestBody SubjectDto request) {
        Subject subject = subjectConverter.maptoEntity(request);
        subject = subjectService.updateSubject(id, subject);

        logger.info("Updated subject {}", subject);
        return subjectConverter.maptoDto(subject);
    }

    @PutMapping("/partial/{id}")
    public SubjectDto updatePartialSubject(@PathVariable Long id, @RequestBody SubjectDto request) {
        Subject subject = subjectConverter.maptoEntity(request);
        subject = subjectService.updatePartialSubject(id, subject);

        logger.info("Updated subject {}", subject);
        return subjectConverter.maptoDto(subject);
    }

    @GetMapping("/points/{credit}")
    public List<SubjectDto> getAllSubjectsByCreditPoints(@PathVariable Integer credit) {
        List<Subject> subjects = subjectService.findAllByCreditPoints(credit);
        return subjectConverter.maptoDtos(subjects);
    }

    @GetMapping("/between/{creditStart}/{creditEnd}")
    public Long getNumberOfSubjectsWithCreditPointsBetween(@PathVariable Integer creditStart, @PathVariable Integer creditEnd) {
        return subjectService.getNumberOfSubjectsWithCreditPointsBetween(creditStart, creditEnd);
    }
}
