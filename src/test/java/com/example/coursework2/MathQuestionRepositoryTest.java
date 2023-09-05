package com.example.coursework2;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.repository.JavaQuestionRepository;
import com.example.coursework2.examinerService.repository.MathQuestionRepository;
import com.example.coursework2.examinerService.repository.QuestionRepository;
import com.example.coursework2.examinerService.service.QuestionAlreadyAddedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.mockito.Mockito.when;

@Repository()
public class MathQuestionRepositoryTest {
    @InjectMocks
    MathQuestionRepository mathRepositoryTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    public void addTest() {
        Question question0 = new Question("1+1", "=2");
        mathRepositoryTest.add(question0);
        Assertions.assertTrue(mathRepositoryTest.getAll().contains(question0));
        Assertions.assertThrows(QuestionAlreadyAddedException.class, () -> mathRepositoryTest.add(question0));
    }

    @Test
    public void removeTest() {
        Question question0 = new Question("1+1", "=2");
        Question question1 = new Question("2*2", "=4");
        mathRepositoryTest.add(question1);
        mathRepositoryTest.remove(question1);
        Assertions.assertFalse(mathRepositoryTest.getAll().contains(question1));
        Assertions.assertThrows(RuntimeException.class, () -> mathRepositoryTest.remove(question0));
    }

    @Test
    public void getAllTest() {
        Question question1 = new Question("1+1", "=2");
        Question question2 = new Question("2*2", "=4");
        Question question3 = new Question("3/3", "=1");
        Collection<Question> testQuestions = List.of(question1, question2, question3);
        for (Question question : testQuestions) {
            mathRepositoryTest.add(question);
        }
        Assertions.assertTrue(mathRepositoryTest.getAll().containsAll(testQuestions));
    }
}
