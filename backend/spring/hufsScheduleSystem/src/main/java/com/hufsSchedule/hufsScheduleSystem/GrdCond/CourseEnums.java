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

    enum ELTTEnum implements CourseEnums {

        A01121("영어학개론(1)", "Introduction to English Linguistics (1)"),
        A01122("영어학개론(2)", "Introduction to English Linguistics (2)"),
        A10203("언어공학개론", "Introduction to Linguistics & Language Technology"),
        A01127("고급영어문법(1)", "Advanced English Grammar(1)"),
        A01128("고급영어문법(2)", "Advanced English Grammar(2)"),
        A01740("", "Critical Writing(General 1)"),
        A01741("", "Critical Writing(Honors 1)"),
        A10104("", "Critical Writing(General 2)"),
        A10105("", "Critical Writing(Honors 2)");
        // 1617 영어학개론 1,2 고급영어문법 1,2
        // 18 영어학개론1, 언어공학개론, 고급영어문법1,2, critical writing 1,2

        final private String korName, engName;

        ELTTEnum(String korName, String engName) {
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

    enum GermanEnum implements CourseEnums {
        A03209("독일어연습B2", "German Excercise B2"),
        A03224("어휘와구문4", "German Words and Sentence 4"),
        A03202("Lesekurs Deutsch2", "German Reading Course 2");

        final private String korName, engName;

        GermanEnum(String korName, String engName) {
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
            return name() ;
        }
    }

    enum ItalianEnum implements  CourseEnums {
        A06105("초급시청각이탈리아어(1)", "Elementry Italian (A/V)(1)"),
        A06215("초급시청각이탈리아어(2)", "Elementry Italian (A/V)(2)"),
        A06103("초급이탈리아어문법(1)", "Elementry Italian Grammar (1)"),
        A06213("초급이탈리아어문법(2)", "Elementry Italian Grammar (2)"),
        A06102("중급이탈리아어강독(1)", "Readings in Intermediate Italian(2)"),
        A06212("중급이탈리아어강독(2)", "Readings in Intermediate Italian(2)");

        final private String korName, engName;

        ItalianEnum(String korName, String engName) {
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
            return name() ;
        }
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
