package com.blogsearch.blog;

import com.blogsearch.blog.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
