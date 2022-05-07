package com.anu.springmysqlserver.exchanges;

import java.util.List;
import lombok.Data;

@Data
public class GetStudentsByBirthYearRequest {
	private List<Integer> birthYearList;
}
