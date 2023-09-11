package com.exam.examserver.repo;

import com.exam.examserver.entity.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
