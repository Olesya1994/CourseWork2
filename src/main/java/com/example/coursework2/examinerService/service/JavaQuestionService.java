package com.example.coursework2.examinerService.service;

import com.example.coursework2.examinerService.demain.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }


    @Override
    public Question add(String questionText, String answer) {
        Question question = new Question(questionText, answer);
        questions.add(question);
        return question;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyAddedException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new RuntimeException();
    }

    @Override
    public Question remove(String questionText, String answer) {
        Question question = new Question(questionText, answer);
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        int i = 0;
        for (Question question : questions) {
            if (index==i){
                return question;
            }
            i++;
        }
        return null;
    }

}
