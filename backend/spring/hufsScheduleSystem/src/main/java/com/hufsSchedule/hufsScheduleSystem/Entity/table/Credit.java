package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="credit_id", unique = true)
    private Long id;

    private int firstMajor; // 주전공

    private int dualMajor; // 이중전공

    private int secondMajor; // 2전공

    private int minor; // 부전공

    private int outDoor; // 실외

    @Column(name="liberal_arts")
    private int liberalArts; // 교양

    private int teaching; // 교직

    private int optional; // 자선

    @Column(name="total_credit")
    private int totalCredit; // 총 취득

    @Column(name = "average_score")
    private float averageScore; // 총 평점

//    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
//    @JoinColumn(name="user_credit", referencedColumnName = "user_id")
//    private Student student;
}
