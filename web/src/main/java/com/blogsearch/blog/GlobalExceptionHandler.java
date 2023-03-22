package com.blogsearch.blog;

import com.blogsearch.blog.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(value = {ConstraintViolationException.class})
   protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
       ErrorResponse responseErrorDTO = new ErrorResponse();
       responseErrorDTO.setMessage("Invalid Input Value");
       responseErrorDTO.setDetail(e.getConstraintViolations().stream().iterator().next().getPropertyPath().toString().split("\\.")[1] + " : " + e.getConstraintViolations().stream().iterator().next().getMessage());

       return new ResponseEntity<>(responseErrorDTO, HttpStatus.BAD_REQUEST);
   }
}
