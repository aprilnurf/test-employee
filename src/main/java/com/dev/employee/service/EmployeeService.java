package com.dev.employee.service;

import com.dev.employee.model.dto.EmployeeResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
  Mono<EmployeeResponseDto> findEmployeeById(Long id);
  Flux<EmployeeResponseDto> findAllEmployee();

}
