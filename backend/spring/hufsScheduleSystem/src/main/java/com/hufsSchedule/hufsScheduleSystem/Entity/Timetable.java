package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="table_id")
    private Long tableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_table", insertable = false, updatable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;

        if(!user.getTimetables().contains(this)) {
            user.getTimetables().add(this);
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timetable_inst_num", insertable = false, updatable = false)
    private Instruction instruction;

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;

        if(!instruction.getTimetables().contains(this)) {
            instruction.getTimetables().add(this);
        }
    }
}
