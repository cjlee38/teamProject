package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserDuplicationException;
import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepository;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto.Res login(UserDto.loginReq login) {
        User user = userRepository.findByStudentNumberAndPassword(login.getStudentNumber(),login.getPassword()).orElseThrow(UserNotFoundException::new);
        UserDto.Res result = UserDto.Res.builder()
                .userId(user.getUserId())
                .build();
        return result;
    }

    private void isExitedStudentNumber (String studentNumber){
        if(userRepository.findByStudentNumber(studentNumber).orElse(null) != null){
            throw new UserDuplicationException(studentNumber);
        }
    }

    public boolean signUp(UserDto.SignUpReq dto){
        isExitedStudentNumber(dto.getStudentNumber());
        userRepository.save(dto.toEntity());
        return true;
    }
}
