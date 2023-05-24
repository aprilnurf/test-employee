package com.dev.employee.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponseDto {

  private Long id;
  private String name;
  private Double salary;
  private String gradeCode;
  private Double totalBonus;

  @Builder
  public EmployeeResponseDto(Long id, String name, Double salary, String gradeCode,
      Double totalBonus) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    this.gradeCode = gradeCode;
    this.totalBonus = totalBonus;
  }
}
