package com.dev.employee;

import com.dev.employee.model.dao.Employee;
import com.dev.employee.model.dao.Grade;
import com.dev.employee.model.dto.EmployeeResponseDto;
import java.util.Collections;
import java.util.List;

public class EmployeeServiceImplTestVariable {

  protected Employee employee = Employee.builder()
      .id(1L)
      .name("John")
      .grade(Grade.builder().id(3L).bonusPercentage(10F).position("Staff").build())
      .salary(5000000.0)
      .build();
  protected List<Employee> employeeList = Collections.singletonList(employee);

  protected EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
      .id(1L)
      .name("John")
      .salary(5000000.0)
      .totalBonus(5500000.0)
      .gradeCode("3:Staff")
      .build();

}
