package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserDuplicationException;
import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto.Res login(UserDto.loginReq login) {
        Student student = userRepository.findByStudentNumberAndPassword(login.getStudentNumber(),login.getPassword())
                .orElseThrow(UserNotFoundException::new);
        UserDto.Res result = UserDto.Res.builder()
                .userId(student.getId())
                .build();
        return result;
    }

    private void validateDuplication(String studentNumber){
        if (userRepository.findByStudentNumber(studentNumber).isPresent())
            throw new UserDuplicationException(studentNumber);
    }

    public boolean signUp(UserDto.SignUpReq dto){
        validateDuplication(dto.getStudentNumber());
        userRepository.save(dto.toEntity());
        return true;
    }
}
