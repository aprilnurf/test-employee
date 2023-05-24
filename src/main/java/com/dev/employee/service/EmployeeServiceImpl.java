package com.dev.employee.service;

import com.dev.employee.repositories.EmployeeRepository;
import com.dev.employee.exceptions.DataNotFoundException;
import com.dev.employee.model.dao.Employee;
import com.dev.employee.model.dto.EmployeeResponseDto;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Mono<EmployeeResponseDto> findEmployeeById(Long id) {
    return Mono.defer(() -> {
      Employee employee = employeeRepository.findByIdAndDeleted(id, false);
      if (Objects.isNull(employee)) {
        return Mono
            .error(new DataNotFoundException(String.format("Data with id: %s is not found", id)));
      }
      return Mono.just(buildEmployeeResponse(employee));
    }).doOnError(e -> log.error("Error when findAllEmployee, exception: ", e));
  }

  @Override
  public Flux<EmployeeResponseDto> findAllEmployee() {
    return Flux.defer(() -> {
      List<Employee> employeeList = employeeRepository.findAllByDeleted(false);
      if (CollectionUtils.isEmpty(employeeList)) {
        return Flux.error(new DataNotFoundException("Data is empty"));
      }
      return Flux.fromIterable(employeeList)
          .map(this::buildEmployeeResponse);
    }).doOnError(e -> log.error("Error when findAllEmployee, exception: ", e));
  }

  private EmployeeResponseDto buildEmployeeResponse(Employee employee) {
    return EmployeeResponseDto.builder().id(employee.getId())
        .name(employee.getName())
        .salary(employee.getSalary())
        .gradeCode(employee.getGrade().getId() + ":" + employee.getGrade().getPosition())
        .totalBonus(
            (employee.getSalary() * employee.getGrade().getBonusPercentage() / 100) + employee
                .getSalary())
        .build();
  }
}
