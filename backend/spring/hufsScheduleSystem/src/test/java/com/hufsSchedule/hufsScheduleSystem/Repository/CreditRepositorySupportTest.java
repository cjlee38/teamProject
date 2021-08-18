package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditRepositorySupportTest {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditRepositorySupport creditRepositorySupport;

    @Autowired
    private UserRepository userRepository;

    User user;
    Credit credit;

    @BeforeEach
    public void init() {
        user = new User();
        user.setUsername("hello");
        user.setPassword("password");
        userRepository.save(user);

        credit = new Credit();
        credit.setUser(user);
        creditRepository.save(credit);

    }

    @Test
    public void findCreditByUser() throws Exception {
        // when
        Credit byUser = creditRepositorySupport.findByUser(user.getId());

        // then
        assertEquals(byUser.getUser().getId(), user.getId());
    }

    @Test
    public void findCreditFromUserEntity() throws Exception {
        credit.setAverageScore(1.23D);
        creditRepository.save(credit);

        Optional<User> findUser = userRepository.findById(user.getId());

        assertEquals(user.getId(), findUser.get().getId());
        assertEquals(findUser.get().getCredit().getAverageScore(), 1.23D);

        // when

        // then
    }
}