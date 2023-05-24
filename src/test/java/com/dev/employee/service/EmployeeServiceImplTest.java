package com.dev.employee.service;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import com.dev.employee.EmployeeServiceImplTestVariable;
import com.dev.employee.model.dto.EmployeeResponseDto;
import com.dev.employee.repositories.EmployeeRepository;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class EmployeeServiceImplTest extends EmployeeServiceImplTestVariable {

  @InjectMocks
  private EmployeeServiceImpl employeeService;
  @Mock
  private EmployeeRepository employeeRepository;

  @Test
  public void findAllEmployeeSuccessTest(){
    Mockito.when(employeeRepository.findAllByDeleted(false))
        .thenReturn(employeeList);

    Flux<EmployeeResponseDto> findAllTest = employeeService.findAllEmployee();

    StepVerifier.create(findAllTest)
        .assertNext(result -> {
          Mockito.verify(employeeRepository).findAllByDeleted(false);
          Assertions.assertEquals(employeeResponseDto, result);
        }).verifyComplete();
  }

  @Test
  public void findAllEmployeeErrorTest(){

    Mockito.when(employeeRepository.findAllByDeleted(false))
        .thenReturn(Collections.emptyList());

    Flux<EmployeeResponseDto> findAllTest = employeeService.findAllEmployee();

    StepVerifier.create(findAllTest)
        .expectErrorMessage("Data is empty")
        .verify();

    Mockito.verify(employeeRepository).findAllByDeleted(false);
  }

  @Test
  public void findEmployeeByIdSuccessTest(){
    Mockito.when(employeeRepository.findByIdAndDeleted(1L, false))
        .thenReturn(employee);

    Mono<EmployeeResponseDto> findAllTest = employeeService.findEmployeeById(1L);

    StepVerifier.create(findAllTest)
        .assertNext(result -> {
          Mockito.verify(employeeRepository).findByIdAndDeleted(1L, false);
          Assertions.assertEquals(employeeResponseDto, result);
        }).verifyComplete();
  }

  @Test
  public void findEmployeeByIdErrorTest(){

    Mockito.when(employeeRepository.findByIdAndDeleted(1L, false))
        .thenReturn(null);

    Mono<EmployeeResponseDto> findById = employeeService.findEmployeeById(1L);

    StepVerifier.create(findById)
        .expectErrorMessage("Data with id: 1 is not found")
        .verify();

    Mockito.verify(employeeRepository).findByIdAndDeleted(1L, false);
  }

  @BeforeEach
  public void setUp() {
    initMocks(this);
  }

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(this.employeeRepository);
  }

}
