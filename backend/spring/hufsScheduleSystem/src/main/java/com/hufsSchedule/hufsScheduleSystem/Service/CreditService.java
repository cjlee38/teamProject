package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepository;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    public Optional<User> findByUserId(Long userId){
        return userRepository.findById(userId);
    }

    public Optional<Credit> getMyCredit(Long userId){
        return creditRepository.findByUser(findByUserId(userId));
    }
}
