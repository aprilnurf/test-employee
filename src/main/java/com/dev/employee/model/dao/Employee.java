package com.dev.employee.model.dao;

import com.dev.employee.model.constant.BaseSQLFields;
import com.dev.employee.model.constant.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.EMPLOYEE)
@NoArgsConstructor
@Data
public class Employee extends BaseSQL<Long> {

  private String name;
  private double salary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_code", referencedColumnName = BaseSQLFields.ID, updatable = false)
  private Grade grade;

  @Builder
  public Employee(Long id, Integer version, boolean deleted, String name,
      double salary, Grade grade) {
    super(id, version, deleted);
    this.name = name;
    this.salary = salary;
    this.grade = grade;
  }
}
