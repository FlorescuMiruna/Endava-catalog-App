package com.project.catalog.config;

import com.project.catalog.model.Subject;
import com.project.catalog.model.Teacher;
import com.project.catalog.repository.SubjectRepository;
import com.project.catalog.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnExpression("${insert.start.data}")
public class DataSetup implements ApplicationRunner {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

//    @Value("${insert.start.data}")
//    private Boolean insertData;

    @Autowired
    public DataSetup(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        if (insertData) {
        saveSubject("OOP", 7, false, 60, 40);
        saveSubject("Math", 6, false, 70, 30);
        saveSubject("Chemistry", 4, true, 60, 40);
        saveSubject("Biology", 5, true, 60, 40);
        saveSubject("Sport", 2, true, 90, 10);
        saveSubject("Data Structure", 6, false, 60, 40);

        addTeacher("John", "Doe", "2344565748391", 99999L);
        addTeacher("Maria", "Constantin", "2344565748392", 99999L);
        addTeacher("Mihai", "Popescu", "2344565748393", 99999L);
        addTeacher("Daniela", "Cocos", "2344565748394", 99999L);
        addTeacher("Dan", "Negru", "2344565748395", 99999L);
//        }
    }

    private void saveSubject(String name, Integer creditPoints, Boolean isOptional, Integer coursePercent, Integer seminaryPercent) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCreditPoints(creditPoints);
        subject.setIsOptional(isOptional);
        subject.setCoursePercent(coursePercent);
        subject.setSeminaryPercent(seminaryPercent);

        subjectRepository.save(subject);
    }

    private void addTeacher(String firstName, String lastName, String cnp, Long salary) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setCnp(cnp);
        teacher.setSalary(salary);
        teacher.setBirthDate(LocalDate.now());
        teacher.setEmploymentDate(LocalDate.now());

        teacherRepository.save(teacher);
    }
}
