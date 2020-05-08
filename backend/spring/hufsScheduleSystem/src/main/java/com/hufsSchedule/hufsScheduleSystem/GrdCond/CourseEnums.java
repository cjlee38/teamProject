package com.hufsSchedule.hufsScheduleSystem.GrdCond;

//1. MajorCond의 major class의 enum을 static하게 만들기
public interface CourseEnums {
    public String getCourseKorName();
    public String getCourseEngName();

    enum BusinessEnum implements CourseEnums{
        D012033("경영학원론", "Principle of MajorCond.Business Management"),
        D01205("회계원리", "Principles of Accounting"),
        D03103("경영통계학", "MajorCond.Business Statistics"),
        D03210("운영관리", "Operations Management"),
        D01405("마케팅관리", "Marketing Management"),
        D01314("조직행동", "Organizational Behavior"),
        D01311("재무관리", "Financial Management"),
        D03205("재무회계(1)", "Intermediate Accounting (1)"),
        D01305("국제경영론", "International MajorCond.Business Managemnet"),
        P042052("경영정보학개론", "Introduction to Management Information System");

        final private String korName, engName;

        BusinessEnum(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }
        @Override
        public String getCourseKorName() {
            return korName;
        }
        @Override
        public String getCourseEngName() {
            return engName;
        }
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
        public String getCourseKorName() {
            return korName;
        }
        @Override
        public String getCourseEngName() {
            return engName;
        }
    }

    enum


}
