package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "user_course", insertable = false, updatable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;

        if(!user.getCourses().contains(this)) {
            user.getCourses().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "course_inst_num", insertable = false, updatable = false)
    private Instruction instruction;

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;

        if(!instruction.getCourses().contains(this)) {
            instruction.getCourses().add(this);
        }
    }
}
