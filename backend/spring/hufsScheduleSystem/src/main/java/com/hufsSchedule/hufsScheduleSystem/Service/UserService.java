package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserDuplicationException;
import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User login(Long studentNumber, String password) {
        return userRepository.findByStudentNumberAndPassword(studentNumber,password).orElseThrow(UserNotFoundException::new);
    }

    private void isExitedStudentNumber (Long studentNumber){
        if(userRepository.findByStudentNumber(studentNumber).orElse(null) != null){
            throw new UserDuplicationException(studentNumber);
        }
    }


    public User signUp(UserDto.SignUpReq dto){
        isExitedStudentNumber(dto.getStudentNumber());
        return userRepository.save(dto.toEntity());
    }
}
