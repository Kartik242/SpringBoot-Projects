package com.kartikeye.question_service.service;


import com.kartikeye.question_service.model.Question;
import com.kartikeye.question_service.model.QuestionWrapper;
import com.kartikeye.question_service.model.Response;
import com.kartikeye.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> geQuestionByCategory(String category) {

        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {

        try {
            questionRepo.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        List<Integer> questions = questionRepo.findQuestionByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        try {

            int score = 0;
            for (Response r : responses) {
                Question question = questionRepo.findById(r.getId()).get();
                if (r.getResponse().equals(question.getRightAnswer())) {
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }

    }
}
