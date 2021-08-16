package com.hufsSchedule.hufsScheduleSystem.Redis;

import com.hufsSchedule.hufsScheduleSystem.Dto.RedisDto;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final InstructionRepository instructionRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void InstallInstructions() {
        List<Instruction> instructions = instructionRepository.findAllByYear(20L);
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        //get/set을 위한 객체
        for (Instruction instruction : instructions){
            RedisDto setData = new RedisDto(instruction.getId(), instruction);
            vop.set("key", setData);
            RedisDto getData = (RedisDto) vop.get("key");
            System.out.println(getData.getInstructionId());
            System.out.println(getData.getInstruction().getSubject());
        }
    }
}
