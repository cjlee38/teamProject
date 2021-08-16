package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.QCredit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CreditRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public CreditRepositorySupport (JPAQueryFactory queryFactory){
        super(Credit.class);
        this.queryFactory = queryFactory;
    }
    public Credit findByUser(Long userId){
        QCredit credit = new QCredit("credit");
        return queryFactory.selectFrom(credit).where(credit.student.id.eq(userId)).fetchOne();
    }
}
