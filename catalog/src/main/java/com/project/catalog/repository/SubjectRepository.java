package com.project.catalog.repository;

import com.project.catalog.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByCreditPoints(Integer creditPoints); // select * from subject where ...

    Long countByCreditPointsBetween(Integer creditStart, Integer creditEnd);
}
