package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public CourseRepositorySupport(JPAQueryFactory queryFactory){
        super(Course.class);
        this.queryFactory = queryFactory;
    }

    public List<TimetableDto.findInstructionCode> findInstructionCodeByMajor(String area){
        QCourse course = new QCourse("course");
        QInstruction instruction = new QInstruction("instruction");
        List<TimetableDto.findInstructionCode> dto = queryFactory
                .select(Projections.bean(TimetableDto.findInstructionCode.class, course.user.userId, course.instruction.instructionNumber))
                .from(course)
                .where(course.dept.eq(area)) // 1전공 -> 실제 전공명으로 수정되야함
                .fetch();
        return dto;
    }

    public List<Instruction> findInstructionByUser(Long userId){
        QCourse course = new QCourse("course");
        QInstruction instruction = new QInstruction("instruction");
        return queryFactory
                .select(instruction)
                .from(instruction)
                .where(instruction.instructionId.in(
                        JPAExpressions
                                .select(course.instruction.instructionId)
                                .from(course)
                                .where(course.user.userId.eq(userId)
                                        /*course.courseArea.eq("1전공")*/)))
                .fetch();
    }
    public List<Instruction> findInstructionByUserCourseArea(Long userId, String courseAreaName){
        QCourse course = new QCourse("course");
        QInstruction instruction = new QInstruction("instruction");
        return queryFactory
                .select(instruction)
                .from(instruction)
                .where(instruction.instructionId.in(
                        JPAExpressions
                                .select(course.instruction.instructionId)
                                .from(course)
                                .where(course.user.userId.eq(userId),
                                        course.courseArea.eq(courseAreaName))))
                .fetch();
    }
}
