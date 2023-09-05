package com.example.coursework2.examinerService.repository;
import com.example.coursework2.examinerService.demain.Question;
import com.example.coursework2.examinerService.service.QuestionAlreadyAddedException;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("mathRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();


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
}
