package com.example.prepractice.question.controller;


import com.example.prepractice.dto.PageResponseDto;
import com.example.prepractice.dto.SingleResponseDto;
import com.example.prepractice.question.dto.QuestionPatchDto;
import com.example.prepractice.question.dto.QuestionPostDto;
import com.example.prepractice.question.dto.QuestionReponseDto;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.mapper.QuestionMapper;
import com.example.prepractice.question.service.QuestionService;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity questionPost(@RequestBody QuestionPostDto questionPostDto){

        Question question = questionMapper.questionDtoToEntity(questionPostDto);
        User user = userService.verifiedUser(questionPostDto.getUserId());
        question.addUser(user);
        Question save = questionService.post(question);
        QuestionReponseDto questionEntityToDto = questionMapper.questionEntityToDto(save);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionEntityToDto), HttpStatus.CREATED);

    }

    @GetMapping("/{questionId}")
    public ResponseEntity questionGet(@PathVariable Long questionId){

        Question question = questionService.get(questionId);
        QuestionReponseDto reponseDto = questionMapper.questionEntityToDto(question);
        return new ResponseEntity<>(
                SingleResponseDto.of(reponseDto),HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity questionGets(
            @PageableDefault(page=0,size=10,sort="questionId",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        List<Question> questionList = questionService.findAll();
        List<QuestionReponseDto> questionReponseDtos = questionMapper.questionEntityListToResponseDtoList(questionList);

        Page<QuestionReponseDto> questionReponseDtosPage =
                new PageImpl<>(questionReponseDtos,pageable, questionReponseDtos.size());

        PageResponseDto responseDto = PageResponseDto.of(questionReponseDtosPage.getContent()
                ,questionReponseDtosPage);

        return new ResponseEntity<>(
                SingleResponseDto.of(responseDto),HttpStatus.OK
        );

    }


    @PatchMapping("/{questionId}")
    public ResponseEntity questionPatch(@PathVariable Long questionId,
                                        @RequestBody QuestionPatchDto questionPatchDto){

        User user = userService.verifiedUser(questionPatchDto.getUserId());
        Question question = questionMapper.questionPatchDtoToEntity(questionPatchDto);
        question.addUser(user);
        Question update = questionService.patch(question,questionId);

        QuestionReponseDto reponseDto = questionMapper.questionEntityToDto(update);

        return new ResponseEntity<>(
                reponseDto,HttpStatus.OK
        );

    }


    @DeleteMapping("/{questionId}")
    public ResponseEntity questionDelete(@PathVariable Long questionId){

        questionService.delete(questionId);

        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

    @DeleteMapping
    public ResponseEntity questionDeletes(){

        questionService.deletes();
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }


}
