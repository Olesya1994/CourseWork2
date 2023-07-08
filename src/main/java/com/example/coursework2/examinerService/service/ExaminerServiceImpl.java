package com.example.coursework2.examinerService.service;

import com.example.coursework2.examinerService.demain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaService;
    private final Set<Question> randomQuetions;

    public ExaminerServiceImpl() {
        this.javaService = new JavaQuestionService();
        this.randomQuetions = new HashSet<>();
    }


    @Override
    public Collection<Question> getQuestion(int amount) {
        if (javaService.getAll().size()<amount) {
            throw new TooManyRandomQuestionException();
        }
        if (javaService.getAll().size()==amount) {
            return javaService.getAll();
        }
        while (randomQuetions.size()<amount) {
            Question question= javaService.getRandomQuestion();
            if (randomQuetions.contains(question)){
                randomQuetions.remove(question);
            }
            randomQuetions.add(question);
        }
        return Collections.unmodifiableSet(randomQuetions);
    }
}
