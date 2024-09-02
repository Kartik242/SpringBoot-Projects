package com.kartikeye.quiz_service.service;


import com.kartikeye.quiz_service.feign.QuizInterface;
import com.kartikeye.quiz_service.model.QuestionWrapper;
import com.kartikeye.quiz_service.model.Quiz;
import com.kartikeye.quiz_service.model.Response;
import com.kartikeye.quiz_service.repo.QuizRepo;
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
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {
            //let's call the question service using quizinterface which is using feign
            List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            quizRepo.save(quiz);

            return new ResponseEntity<>("Created new quiz", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        try {
            Quiz quiz = quizRepo.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromID(questionIds);
            return questions;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;

    }
}

