package com.anu.springmysqlserver.controller;

import com.anu.springmysqlserver.entity.Department;
import com.anu.springmysqlserver.entity.Student;
import com.anu.springmysqlserver.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dep/")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
