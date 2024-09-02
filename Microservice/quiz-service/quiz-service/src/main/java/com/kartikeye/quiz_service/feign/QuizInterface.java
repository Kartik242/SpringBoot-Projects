package com.kartikeye.quiz_service.feign;

import com.kartikeye.quiz_service.model.QuestionWrapper;
import com.kartikeye.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {


    //generate question for quiz
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);


    //generate question for particular id
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(@RequestBody List<Integer> questionIds);


    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);


}
