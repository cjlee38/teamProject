package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() throws Exception {
        // given
        User user = new User();
        user.setUsername("hello");
        user.setPassword("world");

        // when
        userRepository.save(user);
        // then
        Optional<User> findUser = userRepository.findById(user.getId());
        assertTrue(findUser.isPresent());
        assertEquals(user.getUsername(), findUser.get().getUsername());
    }
}