package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto.Res login(UserDto.loginReq login) {
        /*Student student = userRepository.findByNumberAndPassword(login.getStudentNumber(),login.getPassword())
                .orElseThrow(UserNotFoundException::new);
        UserDto.Res result = UserDto.Res.builder()
                .userId(student.getId())
                .build();
        return result;*/
        return null;
    }

    private void validateDuplication(String studentNumber){
        /*if (userRepository.findByNumber(studentNumber).isPresent())
            throw new UserDuplicationException(studentNumber);*/

    }

    public boolean signUp(UserDto.SignUpReq dto){
        validateDuplication(dto.getStudentNumber());
//        userRepository.save(dto.toEntity());
        return true;
    }
}
