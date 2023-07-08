package com.example.coursework2.examinerService.service;

import com.example.coursework2.examinerService.demain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String questionText, String answer);

    Question add(Question question);
    Question remove(String questionText, String answer);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();


}
