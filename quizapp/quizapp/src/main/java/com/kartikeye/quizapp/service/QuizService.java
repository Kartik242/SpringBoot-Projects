package com.kartikeye.quizapp.service;

import com.kartikeye.quizapp.model.Question;
import com.kartikeye.quizapp.model.QuestionWrapper;
import com.kartikeye.quizapp.model.Quiz;
import com.kartikeye.quizapp.model.Response;
import com.kartikeye.quizapp.repo.QuestionRepo;
import com.kartikeye.quizapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //first we need to find the question for specific category and how much question
        try {
            List<Question> questions = questionRepo.findQuestionByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepo.save(quiz);

            return new ResponseEntity<>("Created new quiz", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        try {
            Optional<Quiz> quiz = quizRepo.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUsers = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUsers.add(qw);
            }
            return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            Optional<Quiz> quiz = quizRepo.findById(id);
            List<Question> questions = quiz.get().getQuestions();

            int score = 0;
            int i = 0;
            for (Response r : responses) {
                if (r.getResponse().equals(questions.get(i).getRightAnswer())) {
                    score++;
                }
                i++;
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }
}
