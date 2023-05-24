package com.dev.employee.controller;

import com.dev.employee.model.constant.ApiPath;
import com.dev.employee.model.dto.EmployeeResponseDto;
import com.dev.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Validated
@RestController
@Slf4j
@RequestMapping(ApiPath.EMPLOYEE_BASE_PATH)
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping()
  public Flux<EmployeeResponseDto> findAllEmployee() {
    return employeeService.findAllEmployee();
  }

  @GetMapping("/{employeeId}")
  public Mono<EmployeeResponseDto> findEmployeeById(
      @PathVariable("employeeId") Long employeeId) {
    return employeeService.findEmployeeById(employeeId);
  }


}
