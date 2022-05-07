package com.anu.springmysqlserver.service;

import com.anu.springmysqlserver.entity.Department;
import com.anu.springmysqlserver.entity.Student;
import com.anu.springmysqlserver.entity.Subject;
import com.anu.springmysqlserver.exchanges.CreateStudentRequest;
import com.anu.springmysqlserver.exchanges.CreateSubjectRequest;
import com.anu.springmysqlserver.exchanges.GetStudentsByBirthYearRequest;
import com.anu.springmysqlserver.exchanges.UpdateStudentRequest;
import com.anu.springmysqlserver.repository.DepartmentRepository;
import com.anu.springmysqlserver.repository.StudentRepository;
import com.anu.springmysqlserver.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public Student createStudent(CreateStudentRequest createStudentRequest) {
        logger.error(String.valueOf(createStudentRequest));
        logger.info("Hello World");
        logger.info(createStudentRequest.getSubjectList().toString());

        Department department = new Department(createStudentRequest.getDepartment().getDepName());
        departmentRepository.save(department);

        List<Subject> subjects = new ArrayList<>();
        if(createStudentRequest.getSubjectList() != null) {
            for (CreateSubjectRequest createSub: createStudentRequest.getSubjectList()) {
                Subject subject = new Subject(createSub.getSubjectName());
                subjects.add(subject);

                subjectRepository.save(subject);
            }
        }

        Student student = new Student(createStudentRequest.getName(),
                createStudentRequest.getCountry(),
                createStudentRequest.getBirthYear(),
                department, subjects);
        studentRepository.save(student);

        return student;
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();
        student.setName(updateStudentRequest.getName());
        student.setCountry(updateStudentRequest.getCountry());
        student.setBirthYear(updateStudentRequest.getBirthYear());
        studentRepository.save(student);
        return student;
    }

    public String deleteStudent(long id) {
        studentRepository.deleteById(id);
        return "Student Deleted";
    }

    public List<Student> getStudentByNameAndBirthYear(String name, Integer birthYear) {
        return studentRepository.findByNameAndBirthYear(name, birthYear);
    }

    public List<Student> getStudentByNameOrBirthYear(String name, Integer birthYear) {
        return studentRepository.findByNameOrBirthYear(name, birthYear);
    }

    public List<Student> getStudentsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getStudentsWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return studentRepository.findAll(sort);
    }

    public List<Student> getStudentsByNameLike(String name) {
        logger.warn("IDHAR H KYA " + name);

        List<Student> students = null;
        try {
            students = studentRepository.findByNameLike("%" + name + "%");
        } catch (Error e) {
            logger.info(e.toString());
        }

        return students;
    }

    public List<Student> getStudentsByNameStartsWith(String name) {
        return studentRepository.findByNameStartsWith(name);
    }

}
