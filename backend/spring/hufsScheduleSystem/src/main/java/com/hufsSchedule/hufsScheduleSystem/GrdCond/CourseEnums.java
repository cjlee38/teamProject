package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;

public interface CourseEnums {
    public String getKorName();
    public String getEngName();
    public String getCourseNumber();

    enum BusinessEnum implements CourseEnums{
        D01203("경영학원론", "Principle of MajorCond.Business Management"),
        D01205("회계원리", "Principles of Accounting"),
        D03103("경영통계학", "MajorCond.Business Statistics"),
        D03210("운영관리", "Operations Management"),
        D01405("마케팅관리", "Marketing Management"),
        D01314("조직행동", "Organizational Behavior"),
        D01311("재무관리", "Financial Management"),
        D03205("재무회계(1)", "Intermediate Accounting (1)"),
        D01305("국제경영론", "International MajorCond.Business Managemnet"),
        P04205("경영정보학개론", "Introduction to Management Information System");

        final private String korName, engName;

        BusinessEnum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }
        @Override
        public String getKorName() {
            return korName;
        }
        @Override
        public String getEngName() {
            return engName;
        }
        @Override
        public String getCourseNumber() { return name(); }
    }

    enum SoftwareConvergenceEnum implements CourseEnums{
        F05102("컴퓨터프로그래밍1", "Computer Programming 1"),
        T05202("컴퓨터프로그래밍2", "Computer Programming 2"),
        V44303("자료구조", "Data Structure"),
        T04342("알고리즘", "Algorithm"),
        F05454("소프트웨어공학", "Software Engineering"),
        T07403("종합설계", "Capstone Design");

        final private String korName, engName;

        SoftwareConvergenceEnum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }
        @Override
        public String getKorName() {
            return korName;
        }
        @Override
        public String getEngName() {
            return engName;
        }
        @Override
        public String getCourseNumber() { return name(); }
    }

    enum RussianEnum implements CourseEnums {

        A04131("러시아어말하기(1)", "Russian Conversation(1)"),
        A04132("러시아어말하기(2)", "Russian Conversaiton(2)"),
        A04223("러시아어말하기심화(1)", "Advanced Russian Conversation(1)"),
        A04224("러시아어말하기심화(2)", "Advanced Russain Conversation(2)");

        final private String korName, engName;

        RussianEnum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }

        @Override
        public String getKorName() { return korName; }
        @Override
        public String getEngName() { return engName; }
        @Override
        public String getCourseNumber() { return name(); }
    }

    enum VietnameseEnum implements  CourseEnums {
        B06105("초급베트남어회화(1)", "Elementry Vietnamese Conversation(1)"),
        B06106("초급베트남어회화(2)", "Elementry Vietnamese Conversation(2)"),
        B06222("중급베트남어회화(1)", "Intermediate Vietnamese Conversation(1)"),
        B06223("중급베트남어회화(2)", "Intermediate Vietnamese Conversation(2)"),
        B06311("고급베트남어회화(1)", "Intermediate Vietnamese Conversation(1)"),
        B06312("고급베트남어회화(2)", "Intermediate Vietnamese Conversation(2)"),
        Z99999("베트남역사_최소과목", "History of Vietnamese"),
        Z99998("베트남문학이해_최소과목", "Understanding of Vietnamese Literature");

        final private String korName, engName;

        VietnameseEnum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }

        @Override
        public String getKorName() {
            return korName;
        }

        @Override
        public String getEngName() {
            return engName;
        }
        @Override
        public String getCourseNumber() { return name(); }
    }

    enum LibArts2015Enum implements CourseEnums {
        Y12101("미네르바인문(1)읽기와쓰기", "Minerva Humanities Reading and Writing"),
        Y12102("미네르바인문(2)읽기와토의.토론", "Minerva Humanities Reading and Discussion & Debate"),
        U7618("신입생세미나", "Seminar for Freshmen"),
        Y12104("HUFS Career Design 진로설정과취.창업경력개발", "HUFS Career Design (Defining Career Path & Development)");

        final private String korName, engName;

        LibArts2015Enum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }

        @Override
        public String getKorName() {
            return korName;
        }
        @Override
        public String getEngName() {
            return engName;
        }
        @Override
        public String getCourseNumber() {
            return name();
        }

    }



}
