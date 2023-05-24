package com.dev.employee.model.dao;

import com.dev.employee.model.constant.BaseSQLFields;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseSQL<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = -6012492111193558330L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = BaseSQLFields.ID, updatable = false)
  private T id;

  @Version
  @Column(name = BaseSQLFields.VERSION)
  private Integer version = 0;

  @Column(name = BaseSQLFields.IS_DELETED)
  private boolean deleted = false;

}
