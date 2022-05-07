package com.anu.controller;

import com.anu.entity.Department;
import com.anu.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/neo4j/dep/")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
