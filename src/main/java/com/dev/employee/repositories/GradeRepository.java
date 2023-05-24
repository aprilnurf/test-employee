package com.dev.employee.repositories;

import com.dev.employee.model.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Employee, Long> {

}
