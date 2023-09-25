package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

//    @DeleteMapping("/{qid}")
//    public ResponseEntity<String> deleteQuiz(@PathVariable ("qid") Long quizId){
//        this.quizService.deleteQuiz(quizId);
//        return new ResponseEntity("hello", HttpStatus.OK);
//    }qid


//    @DeleteMapping("/{qid}")
//    public String deleteQuiz(@PathVariable ("qid") Long qid){
//        this.quizService.deleteQuiz(qid);
//        return "the id has been deleted";
//    }


//    @DeleteMapping("/{qid}")
//    public ResponseEntity<String> deleteQuiz(@PathVariable("qid") Long qid) {
//        try {
//            this.quizService.deleteQuiz(qid);
//            return ResponseEntity.ok("Quiz successfully deleted");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting quiz: " + e.getMessage());
//        }

//    @DeleteMapping("/{quizId}")
//    public ResponseEntity<Map<String, String>> deleteQuiz(@PathVariable("quizId") Long quizId) {
//        try {
//            this.quizService.deleteQuiz(quizId);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "Quiz with ID " + quizId + " has been deleted successfully");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "Failed to delete quiz with ID " + quizId);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }


//    @DeleteMapping("/{qid}")
//    public ResponseEntity<String> deleteQuiz(@PathVariable ("qid") Long qid){
//        this.quizService.deleteQuiz(qid);
//        return ResponseEntity.ok("has been deleted"+qid);
//    }
    @DeleteMapping("/{qid}")

    public  ResponseEntity<String> deleteQuiz(@PathVariable ("qid") Long qid,String responseMessage, HttpStatus httpStatus) {
        this.quizService.deleteQuiz(qid);
        responseMessage="the id has been deleted";
        httpStatus=HttpStatus.OK;
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }




}
