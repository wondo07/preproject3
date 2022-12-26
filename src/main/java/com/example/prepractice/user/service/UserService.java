package com.example.prepractice.user.service;

import com.example.prepractice.constant.BusinessLogicException;
import com.example.prepractice.constant.ExceptionCode;
import com.example.prepractice.user.entity.User;
import com.example.prepractice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // 사용자 생성자
    public User save(User user) {

        User saved  = userRepository.save(user);

        return saved;
    }



    public User get(Long userId) {
        User user = verifiedUser(userId);

        return user;
    }


    public User update(User user, Long userId) {
        User findUser = verifiedUser(userId);

        Optional.ofNullable(user.getPassword())
                        .ifPresent(password -> findUser.setPassword(password));

        Optional.ofNullable(user.getDisplayName())
                .ifPresent(displayname -> findUser.setDisplayName(displayname));

        return findUser;
    }

    public void delete(Long userId) {

      userRepository.deleteById(userId);

    }

    public void deletes() {
        userRepository.deleteAll();
    }


    public User verifiedUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        return user;

    }
}
