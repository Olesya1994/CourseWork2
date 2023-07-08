package com.example.coursework2.examinerService.service;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;

@Qualifier("mathService")
@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public MathQuestionService(@Qualifier("mathRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String questionText, String answer) {
        return repository.add(questionText, answer);
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Question remove(String questionText, String answer) {
        return repository.remove(questionText, answer);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(repository.getAll().size());
        int i = 0;
        for (Question question : repository.getAll()) {
            if (index == i) {
                return question;
            }
            i++;
        }
        return null;
    }
}

