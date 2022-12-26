package com.example.prepractice.user.mapper;

import com.example.prepractice.constant.LoginType;
import com.example.prepractice.constant.UserStatus;
import com.example.prepractice.user.dto.UserPatchDto;
import com.example.prepractice.user.dto.UserPostDto;
import com.example.prepractice.user.dto.UserResponseDto;
import com.example.prepractice.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User UserDtoToEntity(UserPostDto userPostDto){
        User user = new User();
        user.setEmail(userPostDto.getEmail());
        user.setDisplayName(userPostDto.getDisplayName());
        user.setPassword(userPostDto.getPassword());
        user.setLoginType(LoginType.BASIC);
        user.setUserStatus(UserStatus.ACTIVITY);
        user.setEmailNotice(true);

        return user;
    }


    UserResponseDto UserEntityToReponseDto(User save);

    User UserPatchDtoToEntity(UserPatchDto userPatchDto);
}
