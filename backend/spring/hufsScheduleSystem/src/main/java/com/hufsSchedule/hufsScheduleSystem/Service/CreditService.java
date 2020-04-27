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
    private final UserRepository userRepository;

    public User findByUserId(Long userId){
        final Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    public Credit getMyCredit(Long userId){
        final User user = findByUserId(userId);
        final Credit credit = user.getCredit();
        return credit;
    }
}
