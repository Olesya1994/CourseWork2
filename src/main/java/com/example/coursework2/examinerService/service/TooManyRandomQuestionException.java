package com.example.coursework2.examinerService.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyRandomQuestionException extends RuntimeException {
}
