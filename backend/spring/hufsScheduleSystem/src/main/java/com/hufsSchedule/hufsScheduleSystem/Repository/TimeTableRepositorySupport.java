package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeTableRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public TimeTableRepositorySupport(JPAQueryFactory queryFactory){
        super(Timetable.class);
        this.queryFactory = queryFactory;
    }
    public List<Instruction> findInstructionByUser(Long userId){
        QTimetable timetable = new QTimetable("timetable");
        QInstruction instruction = new QInstruction("instruction");
        return queryFactory
                .select(instruction)
                .from(instruction)
                .where(instruction.instructionId.in(
                        JPAExpressions
                                .select(timetable.instruction.instructionId)
                                .from(timetable)
                                .where(timetable.user.userId.eq(userId))))
                .fetch();
    }
}
