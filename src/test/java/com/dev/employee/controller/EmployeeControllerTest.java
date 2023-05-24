package com.dev.employee.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.dev.employee.EmployeeServiceImplTestVariable;
import com.dev.employee.model.dto.EmployeeResponseDto;
import com.dev.employee.service.EmployeeService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeControllerTest extends EmployeeServiceImplTestVariable {

  @InjectMocks
  private EmployeeController employeeController;

  @Mock
  private EmployeeService employeeService;

  private WebTestClient webTestClient;

  @Test
  public void findByIdTest() throws Exception {
    when(employeeService
        .findEmployeeById(1L))
        .thenReturn(Mono.just(employeeResponseDto));

    EmployeeResponseDto result = webTestClient.get()
        .uri("/com-test-employee/{employeeId}", 1L)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .returnResult(EmployeeResponseDto.class)
        .getResponseBody().blockFirst();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(employeeResponseDto, result);

    verify(employeeService).findEmployeeById(1L);
  }

  @Test
  public void findAllEmployeeTest() throws Exception {
    when(employeeService
        .findAllEmployee())
        .thenReturn(Flux.just(employeeResponseDto));

    List<EmployeeResponseDto> result = webTestClient.get()
        .uri("/com-test-employee")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .returnResult(EmployeeResponseDto.class)
        .getResponseBody()
        .collectList().block();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(Collections.singletonList(employeeResponseDto), result);

    verify(employeeService).findAllEmployee();
  }

  @BeforeEach
  public void setUp() {
    openMocks(this);
    webTestClient = WebTestClient
        .bindToController(
            new EmployeeController(employeeService))
//        .controllerAdvice(new CommonRequestHandlerController())
//        .webFilter(new InterceptorRequest())
        .contentTypeResolver(builder -> builder.fixedResolver(MediaType.APPLICATION_JSON))
        .pathMatching(pathMatchConfigurer -> pathMatchConfigurer.setUseCaseSensitiveMatch(false))
        .corsMappings(registry -> registry
            .addMapping("/**").allowedOrigins("*")
            .allowedMethods("GET"))
        .build();
  }

  @AfterEach
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(this.employeeService);
  }

}
