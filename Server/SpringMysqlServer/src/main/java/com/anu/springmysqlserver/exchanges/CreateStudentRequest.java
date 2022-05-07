package com.anu.springmysqlserver.exchanges;

import lombok.Data;

import java.util.List;

@Data
public class CreateStudentRequest {
	private String name;
	private Integer birthYear;
	private String country;
	private List<CreateSubjectRequest> subjectList;
	private CreateDepartmentRequest department;
}
