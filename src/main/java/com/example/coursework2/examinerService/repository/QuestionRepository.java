package com.example.coursework2.examinerService.repository;

import com.example.coursework2.examinerService.demain.Question;
import java.util.Collection;

public interface QuestionRepository {
    Question add(String questionText, String answer);

    Question add(Question question);

    Question remove(Question question);

    Question remove(String questionText, String answer);

    Collection<Question> getAll();

}
