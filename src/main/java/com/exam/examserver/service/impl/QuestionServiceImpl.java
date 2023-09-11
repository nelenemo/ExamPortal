package com.exam.examserver.service.impl;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.repo.QuestionRepository;
import com.exam.examserver.repo.QuizRepository;
import com.exam.examserver.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
private final QuizRepository quizRepository;

    @Override
    public Question addQuestion(Question question) {

        if (question.getQuiz() != null && question.getQuiz().getQId() != null) {
            // Load the existing Quiz entity from the database
            Quiz existingQuiz = quizRepository.findById(question.getQuiz().getQId())
                    .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + question.getQuiz().getQId()));

            // Set the loaded Quiz as the quiz for the question
            question.setQuiz(existingQuiz);

            // Save the question entity
            Question savedQuestion = questionRepository.save(question);
            return savedQuestion;
        } else {
            throw new IllegalArgumentException("Quiz ID is required for adding a question.");

        }
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return (Set<Question>) this.questionRepository.findAll();
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public void deleteQuestion(Long questionId) {
        this.questionRepository.deleteById(questionId);

    }
}

//  System.out.println(question1.getQueId());
//
//          Question question2=questionRepository.findById(question1.getQueId()).orElseThrow(()->{throw new RuntimeException("hello");});
//
//
//          System.out.println("Quiz Id : "+ question2.getQuiz());
//
//          return question2;