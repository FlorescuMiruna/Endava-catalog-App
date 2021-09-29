package com.project.catalog.service;

import com.project.catalog.exception.BadRequestException;
import com.project.catalog.exception.NotFoundException;
import com.project.catalog.model.Subject;
import com.project.catalog.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service     //service classes contain most of the business logic;
public class SubjectService {

    private final SubjectRepository subjectRepository;
//    private Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

//        if (optionalSubject.isPresent()) {
//            return optionalSubject.get();
//        } else {
////            logger.warn("Subject not found!");
//            throw new RuntimeException("Subject not found!");
//        }

        return optionalSubject.orElseThrow(() -> new NotFoundException("Subject not found!", "subject.not.found"));
    }

    public Subject saveSubject(Subject subject) {
        validateSubjectScoring(subject);
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectRepository.delete(subject.get());
        } else {
            throw new NotFoundException("Subject not found!", "subject.not.found");
        }
    }

    public Subject updateSubject(Long id, Subject subjectUpdated) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);

        if (subjectOptional.isPresent()) {
            subjectUpdated.setId(id);
            return subjectRepository.save(subjectUpdated);
        } else {
            throw new NotFoundException("Subject not found!", "subject.not.found");
        }
    }

    public Subject updatePartialSubject(Long id, Subject subjectUpdated) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);

        if (subjectOptional.isPresent()) {
            subjectUpdated.setId(id);
            subjectUpdated.setName(subjectUpdated.getName() == null ? subjectOptional.get().getName() : subjectUpdated.getName());
            subjectUpdated.setIsOptional(subjectUpdated.getIsOptional() == null ? subjectOptional.get().getIsOptional() : subjectUpdated.getIsOptional());
            subjectUpdated.setCreditPoints(subjectUpdated.getCreditPoints() == null ? subjectOptional.get().getCreditPoints() : subjectUpdated.getCreditPoints());
            subjectUpdated.setCoursePercent(subjectUpdated.getCoursePercent() == null ? subjectOptional.get().getCoursePercent() : subjectUpdated.getCoursePercent());
            subjectUpdated.setSeminaryPercent(subjectUpdated.getSeminaryPercent() == null ? subjectOptional.get().getSeminaryPercent() : subjectUpdated.getSeminaryPercent());
            return subjectRepository.save(subjectUpdated);
        } else {
            throw new NotFoundException("Subject not found!", "subject.not.found");
        }
    }

    public List<Subject> findAllByCreditPoints(Integer points) {
        return subjectRepository.findByCreditPoints(points);
    }

    public Long getNumberOfSubjectsWithCreditPointsBetween(Integer creditStart, Integer creditEnd) {
        return subjectRepository.countByCreditPointsBetween(creditStart, creditEnd);
    }

    private void validateSubjectScoring(Subject subject) {
        if(subject.getCoursePercent() + subject.getSeminaryPercent() != 100)    {
            throw new BadRequestException("Subject scoring percent must be 100%", "subject.scoring.sum.invalid");
        }
    }

//    public void deleteSubject(Long id)  {
//       subjectRepository.deleteById(id);
//    }
}
