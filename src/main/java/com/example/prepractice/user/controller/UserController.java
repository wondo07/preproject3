package com.example.prepractice.user.controller;


import com.example.prepractice.dto.SingleResponseDto;
import com.example.prepractice.user.dto.UserPatchDto;
import com.example.prepractice.user.dto.UserPostDto;
import com.example.prepractice.user.dto.UserResponseDto;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.mapper.UserMapper;
import com.example.prepractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final private UserMapper userMapper;
    final private UserService userService;

    @PostMapping
    public ResponseEntity UserPost(@RequestBody UserPostDto userPostDto){

        User user = userMapper.UserDtoToEntity(userPostDto);
        User save = userService.save(user);

        UserResponseDto responseDto=userMapper.UserEntityToReponseDto(save);

        return new ResponseEntity<>(
                SingleResponseDto.of(responseDto),HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity UserGet(@PathVariable Long userId){

       User user  = userService.get(userId);
       UserResponseDto responseDto = userMapper.UserEntityToReponseDto(user);

       return new ResponseEntity<>(
               SingleResponseDto.of(responseDto),HttpStatus.OK
       );
    }

    @PatchMapping("/{userId}")
    public ResponseEntity UserUpdate(@PathVariable Long userId,
                                     @RequestBody UserPatchDto userPatchDto){


        User user = userMapper.UserPatchDtoToEntity(userPatchDto);

        User updated = userService.update(user,userId);

        UserResponseDto responseDto = userMapper.UserEntityToReponseDto(updated);
        return new ResponseEntity<>(
                SingleResponseDto.of(responseDto),HttpStatus.OK
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity Userdelete(@PathVariable Long userId){

        userService.delete(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity Userdeletes(){
        userService.deletes();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
