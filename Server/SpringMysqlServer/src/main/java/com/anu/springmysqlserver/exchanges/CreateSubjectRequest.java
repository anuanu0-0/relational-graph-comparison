package com.anu.springmysqlserver.exchanges;

import lombok.Data;

@Data
public class CreateSubjectRequest {
	private String subjectName;
	private Long marks;
}
