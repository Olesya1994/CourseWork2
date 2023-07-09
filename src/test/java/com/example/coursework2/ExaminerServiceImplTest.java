package com.example.coursework2;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.service.ExaminerService;
import com.example.coursework2.examinerService.service.QuestionService;
import com.example.coursework2.examinerService.service.TooManyRandomQuestionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImplTest implements ExaminerService {
    private final QuestionService javaService;
    private final QuestionService mathService;
    private final Set<Question> randomQuestions = new HashSet<>();


    public ExaminerServiceImplTest(@Qualifier("javaService") QuestionService javaService, @Qualifier("mathService") QuestionService mathService) {
        this.javaService = javaService;
        this.mathService = mathService;

    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        randomQuestions.addAll(javaService.getAll());
        randomQuestions.addAll(mathService.getAll());
        if (randomQuestions.size() < amount) {
            throw new TooManyRandomQuestionException();
        }
        if (randomQuestions.size() == amount) {
            return Collections.unmodifiableSet(randomQuestions);
        }
        while (randomQuestions.size() < amount) {
            Question question = javaService.getRandomQuestion();
            if (randomQuestions.contains(question)) {
                randomQuestions.remove(question);
            }
            randomQuestions.add(question);
        }
        return Collections.unmodifiableSet(randomQuestions);
    }
}
