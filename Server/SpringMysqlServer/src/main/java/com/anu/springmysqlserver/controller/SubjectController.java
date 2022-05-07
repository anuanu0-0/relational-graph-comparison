package com.anu.springmysqlserver.controller;

import com.anu.springmysqlserver.entity.Subject;
import com.anu.springmysqlserver.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject/")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
