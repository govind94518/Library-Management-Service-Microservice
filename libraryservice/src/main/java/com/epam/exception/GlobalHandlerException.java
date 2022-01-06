package com.epam.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookLimitExceededException .class)
    public ResponseEntity< Map<String,String>>handleBookLimitExceededException (BookLimitExceededException  userException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error",userException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.name());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BookAlreadyIssuedException .class)
    public ResponseEntity< Map<String,String>>handleBookAlreadyIssuedException (BookAlreadyIssuedException userException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error",userException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.name());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity< Map<String,String>>handleUserException(UserException userException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error",userException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.name());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity< Map<String,String>>handleConnectException(ConnectException connectException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error"," connection problem ");
        response.put("status", HttpStatus.NOT_FOUND.name());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity< Map<String,String>>handleHttpClientErrorException(HttpClientErrorException httpClientErrorException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error",httpClientErrorException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.name());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity< Map<String,String>>handleRestClientExceptionException(RestClientException restClientException, WebRequest request){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error","already exist");
        response.put("status", HttpStatus.ALREADY_REPORTED.name());
        return  new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity< Map<String,String>>handleFeignException (FeignException exception){
        Map<String,String>response=new HashMap<>();
        response.put("service","library");
        response.put("timestamp",new Date().toString());
        response.put("error",exception.getMessage());
        response.put("status", String.valueOf(exception.status()));
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
