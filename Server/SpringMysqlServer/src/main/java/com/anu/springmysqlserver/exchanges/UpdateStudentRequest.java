package com.anu.springmysqlserver.exchanges;

import lombok.Data;

@Data
public class UpdateStudentRequest {
	private Long id;
	private String name;
	private String country;
	private Integer birthYear;
}
