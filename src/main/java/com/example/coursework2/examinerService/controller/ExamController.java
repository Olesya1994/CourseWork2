package com.example.coursework2.examinerService.controller;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.service.ExaminerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")
public class ExamController {
    private final ExaminerService examService;

    public ExamController(ExaminerService examService) {
        this.examService = examService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestion(@PathVariable int amount) {
        return examService.getQuestion(amount);
    }

}
