package com.example.prepractice.question.mapper;


import com.example.prepractice.question.dto.*;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.entity.QuestionComment;
import com.example.prepractice.question.entity.QuestionVote;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question questionDtoToEntity(QuestionPostDto questionPostDto);


    QuestionReponseDto questionEntityToDto(Question question);

    List<QuestionReponseDto> questionEntityListToResponseDtoList(List<Question> questionList);

    Question questionPatchDtoToEntity(QuestionPatchDto questionPatchDto);

    QuestionVote questionVotePostDtoToEntity(QuestionVotePostDto questionVotePostDto);

    QuestionVoteResponseDto questionVoteEntityToResponseDto(QuestionVote questionVote);

    QuestionVote questionVotePatchDtoToEntity(QuestionVotePatchDto questionVotePatchDto);

    QuestionComment questionCommentPostDtoToEntity(QuestionCommentPostDto questionCommentPostDto);

    QuestionCommentResponseDto questionCommentEntityToReponseDto(QuestionComment questionComment);
}
