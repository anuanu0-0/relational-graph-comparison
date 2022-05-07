package com.anu.controller;

import com.anu.entity.Subject;
import com.anu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/neo4j/subject/")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
