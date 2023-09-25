package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuestionService;
import com.exam.examserver.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question question1 = this.questionService.addQuestion(question);
        return ResponseEntity.ok(question1);
    }

    @GetMapping("/{queId}")
    public Question getQuiz(@PathVariable("queId") Long queId) {
        return this.questionService.getQuestion(queId);
    }

    //get all question of a quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
//        Quiz quiz = this.quizService.getQuiz(qid);
//        System.out.println("@@@@@@@@@@@@"+ quiz.getQuestions());
//        Set<Question> questions = quiz.getQuestions();
//        for(Question question: questions){
//            String content = question.getContent();
//            String option1 = question.getOption1();
//            String option2=question.getOption2();
//            String option3=question.getOption3();
//            String option4=question.getOption4();
//                    }
//        List list = new ArrayList(questions);
//        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())){
//            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);


//        if(list.size()>Integer.parseInt(quizService.))


        Quiz quiz=new Quiz();
        quiz.setQId(qid);

        Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);
    }


    @GetMapping("/")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return this.questionService.updateQuestion(question);
    }

    @DeleteMapping("/{queId}")
    public String deleteQuestion(@PathVariable("queId") Long queId) {
        this.questionService.deleteQuestion(queId);
        return "the id of the question has been deleted";
    }
}
