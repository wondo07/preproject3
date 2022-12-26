package com.example.prepractice.user.mapper;

import com.example.prepractice.user.dto.UserPatchDto;
import com.example.prepractice.user.dto.UserResponseDto;
import com.example.prepractice.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-24T20:37:43+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto UserEntityToReponseDto(User save) {
        if ( save == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUserId( save.getUserId() );
        userResponseDto.setEmail( save.getEmail() );
        userResponseDto.setPassword( save.getPassword() );
        userResponseDto.setDisplayName( save.getDisplayName() );
        userResponseDto.setEmailNotice( save.isEmailNotice() );
        userResponseDto.setUserStatus( save.getUserStatus() );
        userResponseDto.setLoginType( save.getLoginType() );
        userResponseDto.setCreateAt( save.getCreateAt() );
        userResponseDto.setUpdateAt( save.getUpdateAt() );

        return userResponseDto;
    }

    @Override
    public User UserPatchDtoToEntity(UserPatchDto userPatchDto) {
        if ( userPatchDto == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userPatchDto.getPassword() );
        user.setDisplayName( userPatchDto.getDisplayName() );

        return user;
    }
}
