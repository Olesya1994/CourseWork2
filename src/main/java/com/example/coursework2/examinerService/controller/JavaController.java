package com.example.coursework2.examinerService.controller;
import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService javaService;

    public JavaController(@Qualifier("javaService") QuestionService javaService) {
        this.javaService = javaService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return javaService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        return javaService.remove(question, answer);
    }

    @GetMapping()
    public Collection<Question> getAll() {
        return javaService.getAll();
    }

}
