package com.project.catalog.repository;

import com.project.catalog.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//public interface TeacherRepository extends JpaRepository<Teacher, Long> {
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {

    Optional<Teacher> findByCnp(String cnp);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    List<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
