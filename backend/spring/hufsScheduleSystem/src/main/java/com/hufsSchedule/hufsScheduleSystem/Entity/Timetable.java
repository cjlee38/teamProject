package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="table_id")
    private Long tableId;

    @ManyToOne
    @JoinColumn(name = "user_table", insertable = false, updatable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;

        if(!user.getTimetables().contains(this)) {
            user.getTimetables().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "timetable_inst_num", insertable = false, updatable = false)
    private Instruction instruction;

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;

        if(!instruction.getTimetables().contains(this)) {
            instruction.getTimetables().add(this);
        }
    }
}
