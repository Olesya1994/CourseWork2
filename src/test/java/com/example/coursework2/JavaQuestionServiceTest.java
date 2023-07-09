package com.example.coursework2;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.repository.JavaQuestionRepository;
import com.example.coursework2.examinerService.repository.QuestionRepository;
import com.example.coursework2.examinerService.service.JavaQuestionService;
import com.example.coursework2.examinerService.service.QuestionAlreadyAddedException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.mockito.Mockito.when;


@Service
public class JavaQuestionServiceTest {

    @Mock
JavaQuestionRepository repository;
    @InjectMocks
    JavaQuestionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void addTest() {
        Question question0 = new Question("Вопрос 0", "Ответ 0");
        service.add("Вопрос 0", "Ответ 0");
        Assertions.assertTrue(service.getAll().contains(question0));
        Assertions.assertThrows(QuestionAlreadyAddedException.class, () -> service.add(question0));
    }

    @Test
    public void removeTest() {
        Question question0 = new Question("Вопрос 0", "Ответ 0");
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        service.add("Вопрос 1", "Ответ 1");
        service.remove("Вопрос 1", "Ответ 1");
        Assertions.assertFalse(service.getAll().contains(question1));
        Assertions.assertThrows(RuntimeException.class, () -> service.remove(question0));
    }

    @Test
    public void getAllTest() {
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");
        Question question3 = new Question("Вопрос 3", "Ответ 3");
        Collection<Question> testQuestions = List.of(question1, question2, question3);
        for (Question question : testQuestions) {
            service.add(question);
        }
        Assertions.assertTrue(service.getAll().containsAll(testQuestions));
    }

    @Test
    public void getRandomQuestionTest() {
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");
        Question question3 = new Question("Вопрос 3", "Ответ 3");
        Collection<Question> testQuestions = List.of(question1, question2, question3);
        when(service.getAll()).thenReturn(testQuestions);
        Question question = service.getRandomQuestion();
        Assertions.assertTrue(service.getAll().contains(question));
        Set<Question> questions = new HashSet<>();
        while (questions.size()<service.getAll().size()){
            questions.add(service.getRandomQuestion());
        }
        Assertions.assertTrue(questions.containsAll(service.getAll()));
            }

}
