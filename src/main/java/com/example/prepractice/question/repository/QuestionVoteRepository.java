package com.example.prepractice.question.repository;


import com.example.prepractice.question.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote,Long> {
}
