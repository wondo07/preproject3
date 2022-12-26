package com.example.prepractice.question.service;


import com.example.prepractice.constant.BusinessLogicException;
import com.example.prepractice.constant.ExceptionCode;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.entity.QuestionComment;
import com.example.prepractice.question.repository.QuestionCommentRepository;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final UserService userService;
    private final QuestionService questionService;

    public QuestionComment save(QuestionComment questionComment, Long questionId, Long userId) {
        User user = userService.verifiedUser(userId);
        Question question = questionService.verifiedQuestion(questionId);

        questionComment.addQuestion(question);
        questionComment.addUser(user);

        questionCommentRepository.save(questionComment);

        return questionComment;
    }





    public QuestionComment verifiedQuestionComment(long questionCommentId){
        Optional<QuestionComment> optionalQuestionComment  = questionCommentRepository.findById(questionCommentId);

        QuestionComment questionComment = optionalQuestionComment.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
         );
        return questionComment;

    }

}
