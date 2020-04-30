package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.QCredit;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public CreditRepositorySupport (JPAQueryFactory queryFactory){
        super(Credit.class);
        this.queryFactory = queryFactory;
    }
    public Credit findByUser(Long userId){
        QCredit credit = new QCredit("credit");
        return queryFactory.selectFrom(credit).where(credit.user.userId.eq(userId)).fetchOne();
    }
}
