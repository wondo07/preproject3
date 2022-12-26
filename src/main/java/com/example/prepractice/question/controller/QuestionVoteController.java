package com.example.prepractice.question.controller;


import com.example.prepractice.constant.QuestionStatus;
import com.example.prepractice.dto.SingleResponseDto;
import com.example.prepractice.question.dto.QuestionVotePatchDto;
import com.example.prepractice.question.dto.QuestionVotePostDto;
import com.example.prepractice.question.dto.QuestionVoteResponseDto;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.entity.QuestionVote;
import com.example.prepractice.question.mapper.QuestionMapper;
import com.example.prepractice.question.service.QuestionService;
import com.example.prepractice.question.service.QuestionVoteService;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionvote")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionMapper questionMapper;
    private final QuestionVoteService questionVoteService;
    private final UserService userService;
    private final QuestionService questionService;


    @PostMapping("/{questionId}")
    private ResponseEntity questionVotePost( @PathVariable Long questionId,
            @RequestBody QuestionVotePostDto questionVotePostDto){

        QuestionVote questionVote = questionMapper.questionVotePostDtoToEntity(questionVotePostDto);

        User user = userService.verifiedUser(questionVotePostDto.getUserId());
        questionVote.setUser(user);

        QuestionVote save = questionVoteService.post(questionVote,questionId);
        QuestionVoteResponseDto questionVoteEntityToResponseDto =
                questionMapper.questionVoteEntityToResponseDto(save);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionVoteEntityToResponseDto), HttpStatus.CREATED);
    }

    @PatchMapping("/vote/{questionVoteId}")
    private ResponseEntity questionVotePatch(@PathVariable Long questionVoteId,
                                             @RequestBody QuestionVotePatchDto questionVotePatchDto){

        QuestionVote questionVote = questionMapper.questionVotePatchDtoToEntity(questionVotePatchDto);

        QuestionVote questionVote1 = questionVoteService.patch(questionVote,questionVoteId);


        QuestionVoteResponseDto questionVoteEntityToResponseDto =
                questionMapper.questionVoteEntityToResponseDto(questionVote1);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionVoteEntityToResponseDto), HttpStatus.OK);
    }




}
