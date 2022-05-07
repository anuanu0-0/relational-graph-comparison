package com.anu.springmysqlserver.repository;

import com.anu.springmysqlserver.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameAndBirthYear(String name, Integer birthYear);

    List<Student> findByNameOrBirthYear(String name, Integer birthYear);

    List<Student> findByBirthYearIn(List<Integer> list);

    List<Student> findByNameLike(String name);

    List<Student> findByNameStartsWith(String name);
}
