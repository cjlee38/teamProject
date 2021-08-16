package com.hufsSchedule.hufsScheduleSystem.domain.entity;

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

    private Integer firstMajor; // 주전공

    private Integer dualMajor; // 이중전공

    private Integer secondMajor; // 2전공

    private Integer minor; // 부전공

    private Integer outDoor; // 실외

    private Integer liberalArts; // 교양

    private Integer teaching; // 교직

    private Integer optional; // 자선

    private Integer totalCredit; // 총 취득

    private Double averageScore; // 총 평점

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name="user_id"/*, referencedColumnName = "user_id"*/)
    private User user;
}
