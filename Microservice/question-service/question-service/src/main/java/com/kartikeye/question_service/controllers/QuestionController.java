package com.kartikeye.question_service.controllers;


import com.kartikeye.question_service.model.Question;
import com.kartikeye.question_service.model.QuestionWrapper;
import com.kartikeye.question_service.model.Response;
import com.kartikeye.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.geQuestionByCategory(category);
    }

    @PostMapping("add")
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);

    }

    //generate question for quiz
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    //generate question for particular id
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);

    }


}
