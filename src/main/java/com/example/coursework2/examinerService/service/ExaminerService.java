package com.example.coursework2.examinerService.service;

import com.example.coursework2.examinerService.demain.Question;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
