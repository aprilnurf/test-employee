package com.dev.employee.model.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class BaseResponse<T> {

  private String code;
  private String message;
  private List<String> errors;
  private T data;

  @Builder
  public BaseResponse(String code, String message, List<String> errors, T data) {
    this.code = code;
    this.message = message;
    this.errors = errors;
    this.data = data;
  }
}
