package com.example.prepractice.question.controller;


import com.example.prepractice.dto.SingleResponseDto;
import com.example.prepractice.question.dto.QuestionCommentPostDto;
import com.example.prepractice.question.dto.QuestionCommentResponseDto;
import com.example.prepractice.question.entity.QuestionComment;
import com.example.prepractice.question.mapper.QuestionMapper;
import com.example.prepractice.question.repository.QuestionCommentRepository;
import com.example.prepractice.question.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questioncomment")
@RequiredArgsConstructor
public class QuestionCommentController {

    private final QuestionCommentService questionCommentService;

    private final QuestionMapper questionMapper;

    @PostMapping("/{questionId}")
    public ResponseEntity commentPost(@PathVariable Long questionId,
                                      @RequestBody QuestionCommentPostDto questionCommentPostDto){
        QuestionComment questionComment = questionMapper.questionCommentPostDtoToEntity(questionCommentPostDto);
        QuestionComment save = questionCommentService.save(questionComment,questionId,questionCommentPostDto.getUserId());

        QuestionCommentResponseDto questionCommentResponseDto = questionMapper.questionCommentEntityToReponseDto(save);

        return new ResponseEntity<>(
                SingleResponseDto.of(questionCommentResponseDto), HttpStatus.OK
        );
    }




}
