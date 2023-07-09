package com.example.coursework2;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.repository.JavaQuestionRepository;
import com.example.coursework2.examinerService.repository.QuestionRepository;
import com.example.coursework2.examinerService.service.QuestionAlreadyAddedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.mockito.Mockito.when;

@Repository()
public class JavaQuestionRepositoryTest {
    @InjectMocks
    JavaQuestionRepository javaRepositoryTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void addTest() {
        Question question0 = new Question("Вопрос 0", "Ответ 0");
        javaRepositoryTest.add(question0);
        Assertions.assertTrue(javaRepositoryTest.getAll().contains(question0));
        Assertions.assertThrows(QuestionAlreadyAddedException.class, () -> javaRepositoryTest.add(question0));
    }

    @Test
    public void removeTest() {
        Question question0 = new Question("Вопрос 0", "Ответ 0");
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        javaRepositoryTest.add(question1);
        javaRepositoryTest.remove(question1);
        Assertions.assertFalse(javaRepositoryTest.getAll().contains(question1));
        Assertions.assertThrows(RuntimeException.class, () -> javaRepositoryTest.remove(question0));
    }

    @Test
    public void getAllTest() {
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");
        Question question3 = new Question("Вопрос 3", "Ответ 3");
        Collection<Question> testQuestions = List.of(question1, question2, question3);
        for (Question question:testQuestions){
            javaRepositoryTest.add(question);
        }
        Assertions.assertTrue(javaRepositoryTest.getAll().containsAll(testQuestions));
    }
}
