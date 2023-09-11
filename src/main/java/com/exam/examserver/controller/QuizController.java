package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
        Quiz quiz1 = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable ("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return  ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @PutMapping("/")
    public Quiz updateCategory(@RequestBody Quiz quiz){
        return this.quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{quizId}")
    public String deleteQuiz(@PathVariable ("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
        return "the id has been deleted";
    }
}
