package com.example.prepractice.question.repository;


import com.example.prepractice.question.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment,Long> {
}
