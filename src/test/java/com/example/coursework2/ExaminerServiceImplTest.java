package com.example.coursework2;

import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.service.ExaminerServiceImpl;
import com.example.coursework2.examinerService.service.QuestionService;
import com.example.coursework2.examinerService.service.TooManyRandomQuestionException;
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
public class ExaminerServiceImplTest {
    @InjectMocks
    ExaminerServiceImpl examinerService;
    @Mock
    QuestionService javaService;
    @Mock
    QuestionService mathService;
    private final Set<Question> randomQuestions = new HashSet<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");
        Question question3 = new Question("1+1", "=2");
        Collection<Question> testJavaQuestions = List.of(question1, question2, question3);
        Collection<Question> testMathQuestions = List.of( question3);

        when(javaService.getAll()).thenReturn( testJavaQuestions);
        when(mathService.getAll()).thenReturn( testMathQuestions);

    }

    @Test
    public void getRandomQuestionTest() {

        Assertions.assertThrows(TooManyRandomQuestionException.class, () -> examinerService.getQuestion(10));
        Assertions.assertTrue(examinerService.getQuestion(3).containsAll(randomQuestions));
    }
}
