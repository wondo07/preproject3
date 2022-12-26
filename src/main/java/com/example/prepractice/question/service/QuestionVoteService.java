package com.example.prepractice.question.service;


import com.example.prepractice.constant.BusinessLogicException;
import com.example.prepractice.constant.ExceptionCode;
import com.example.prepractice.constant.QuestionVoteStatus;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.entity.QuestionVote;
import com.example.prepractice.question.repository.QuestionVoteRepository;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final UserService userService;
    private final QuestionService questionService;

    public QuestionVote post(QuestionVote questionVote, long questionId) {


        Question question = questionService.verifiedQuestion(questionId);
        questionVote.addQuestion(question);

        QuestionVote save = questionVoteRepository.save(questionVote);


        return save;
    }


    public QuestionVote verifiedQuestionVote(long questionVoteId) {
        Optional<QuestionVote> optionalQuestionVote = questionVoteRepository.findById(questionVoteId);
        QuestionVote questionVote = optionalQuestionVote.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)
        );
        return questionVote;
    }


    public QuestionVote patch(QuestionVote questionVote, Long questionVoteId) {

        QuestionVote findQuestionVote = verifiedQuestionVote(questionVoteId);

        QuestionVoteStatus vote = questionVote.getQuestionVoteStatus();

        if (vote != null) {
            if (vote.equals(QuestionVoteStatus.UP)) {
                if (vote.equals(findQuestionVote.getQuestionVoteStatus())) {
                    return findQuestionVote;
                } else {
                    Optional.ofNullable(questionVote.getQuestionVoteStatus())
                            .ifPresent(patchedvote -> findQuestionVote.setQuestionVoteStatus(patchedvote));
                }
            } else if (vote.equals(QuestionVoteStatus.DOWN)) {
                if (vote.equals(findQuestionVote.getQuestionVoteStatus())) {
                    return findQuestionVote;
                } else {
                    Optional.ofNullable(questionVote.getQuestionVoteStatus())
                            .ifPresent(patchedvote -> findQuestionVote.setQuestionVoteStatus(patchedvote));
                }
            } else {
                if (vote.equals(QuestionVoteStatus.NONE)) {
                    if (vote.equals(findQuestionVote.getQuestionVoteStatus())) {
                        return findQuestionVote;
                    } else {
                        Optional.ofNullable(questionVote.getQuestionVoteStatus())
                                .ifPresent(patchedvote -> findQuestionVote.setQuestionVoteStatus(patchedvote));
                    }
                }
            }
        }
        return findQuestionVote;
    }
}

//        QuestionVote vote = verifiedQuestionVote(questionVoteId);
//        QuestionVoteStatus comp = vote.getQuestionVoteStatus();
//        if (questionVote.getQuestionVoteStatus() != null) {
//            if (questionVote.getQuestionVoteStatus().equals(QuestionVoteStatus.UP)) {
//                if (comp.equals(QuestionVoteStatus.UP)) {
//                    return vote;
//                } else {
//                    Optional.ofNullable(questionVote.getQuestionVoteStatus())
//                            .ifPresent(vote::setQuestionVoteStatus);
//                }
//            } else {
//                if (comp.equals(QuestionVoteStatus.DOWN)) {
//                    return vote;
//                } else {
//                    Optional.ofNullable(questionVote.getQuestionVoteStatus())
//                            .ifPresent(vote::setQuestionVoteStatus);
//                }
//            }
//        }
//        return vote;



