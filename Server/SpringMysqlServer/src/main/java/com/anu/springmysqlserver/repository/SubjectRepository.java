package com.anu.springmysqlserver.repository;

import com.anu.springmysqlserver.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long> {
    List<Subject> findAll();
}
