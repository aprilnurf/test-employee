package com.dev.employee.repositories;


import com.dev.employee.model.dao.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("SELECT e from Employee e "
      + "LEFT JOIN FETCH e.grade g "
      + "where e.id = :id AND e.deleted = :deleted and g.deleted = :deleted")
  Employee findByIdAndDeleted(Long id, boolean deleted);

  @Query("SELECT e from Employee e "
      + "LEFT JOIN FETCH e.grade g "
      + "where e.grade.id = g.id AND e.deleted = :deleted and g.deleted = :deleted")
  List<Employee> findAllByDeleted(boolean deleted);


}
