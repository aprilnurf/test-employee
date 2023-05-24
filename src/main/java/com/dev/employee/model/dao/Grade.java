package com.dev.employee.model.dao;

import com.dev.employee.model.constant.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.GRADE)
@NoArgsConstructor
@Data
public class Grade extends BaseSQL<Long> {

  private String position;

  @Column(name = "bonus_percentage")
  private Float bonusPercentage;

  @Builder
  public Grade(Long id, Integer version, boolean deleted, String position,
      Float bonusPercentage) {
    super(id, version, deleted);
    this.position = position;
    this.bonusPercentage = bonusPercentage;
  }
}
