package com.example.prepractice.question.service;


import com.example.prepractice.constant.BusinessLogicException;
import com.example.prepractice.constant.ExceptionCode;
import com.example.prepractice.constant.QuestionStatus;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.repository.QuestionRepository;
import com.example.prepractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final UserService userService;

    /* 질문 생성 */
    public Question post(Question question) {

        question.setViewCounting(0);
        question.setQuestionStatus(QuestionStatus.Opend);
        Question save  = questionRepository.save(question);

        return save;
    }

    /* 질문 단건 조회 */
    public Question get(Long questionId) {
        Question question = verifiedQuestion(questionId);

        question.setViewCounting(question.getViewCounting()+1);
        return question;
    }

    /* 질문 전체 조회 */




    public Question verifiedQuestion(Long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question question = optionalQuestion.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return question;
    }

    public List<Question> findAll() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    public Question patch(Question question, Long questionId) {
        Question findQuestion = verifiedQuestion(questionId);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable((question.getBody()))
                .ifPresent(body -> findQuestion.setBody(body));
        Optional.ofNullable(question.getQuestionStatus())
                .ifPresent(questionStatus -> findQuestion.setQuestionStatus(questionStatus));

        return findQuestion;

    }

    public void delete(Long questionId) {

       Question question  = verifiedQuestion(questionId);
       questionRepository.delete(question);

    }

    public void deletes() {

        questionRepository.deleteAll();

    }
}
