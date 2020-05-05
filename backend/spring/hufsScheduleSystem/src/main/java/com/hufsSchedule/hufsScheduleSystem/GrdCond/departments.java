package com.hufsSchedule.hufsScheduleSystem.GrdCond;

public enum departments {
    // 1. 영어이름 something 수정
    // 2. enumname 추가
    // 3. department enum 분리
    ENGLISH1("ELTT/영어학과", "something",),
    ENGLISH2("영미문학.문화학과", "something"),
    ENGLISH3("영어통번역학과", "something"),
    ENGLISH4("EICC학과", "something"),
    WESTERN1("프랑스어과", "something"),
    WESTERN2("프랑스응용어문학전공","something"),
    WESTERN3("FATI전공", "something"),
    WESTERN4("프랑스,EU전공","something"),
    WESTERN5("독일어과","something"),
    WESTERN6("노어과", "something"),
    WESTERN7("이탈리아어과", "something"),
    WESTERN8("포르투갈어과", "something"),
    WESTERN9("네덜란드어과", "something"),
    WESTERN10("스칸디나비아어과","something"),
    ASIA1("마인어과", "something"),
    ASIA2("아랍어과", "something"),
    ASIA3("태국어과", "something"),
    ASIA4("베트남어과", "something"),
    ASIA5("인도어과", "something"),
    ASIA6("터키,아제르바이잔어과","something"),
    ASIA7("이란어과","something"),
    ASIA8("몽골어과","something"),
    CHINESE1("중국언어문화학부", "something"),
    CHINESE2("중국외교통상학부", "something"),
    JAPANESE1("일본언어문화학부", "something"),
    JAPANESE2("융합일본지역학부", "something"),
    SOCIALSCIENCE1("정치외교학과", "something"),
    SOCIALSCIENCE2("행정학과", "something"),
    SOCIALSCIENCE3("미디어커뮤니케이션학부", "something"),
    SOCIALSCIENCE4("광고.PR.브랜딩","something"),
    SOCIALSCIENCE5("방송.영상.뉴미디어", "something"),
    SOCIALSCIENCE6("언론정보","something"),
    BUSINESSADMINISTRATION1("국제통상학과","something"),
    BUSINESSADMINISTRATION2("경제학부", "something"),
    BUSINESS("경영학부", "something", "BusinessEnum"),
    EDUCATION1("영어교육과", "something"),
    EDUCATION2("프랑스어교육과", "something"),
    EDUCATION3("독일어교육과", "something"),
    EDUCATION4("한국어교육과", "something"),
    EDUCATION5("중국어교육과", "something"),
    EDUCATION6("교육학전공", "something"),
    EDUCATION7("체육학전공", "something"),
    INTERNATIONAL("국제학부", "something"),
    LD1("LD전공", "something"),
    LD2("사회과학전공", "something"),
    LT1("LT학부", "something");

    final private String korName, engName, enumName;

    departments(String korName, String engName, String enumName) {
        this.korName = korName;
        this.engName = engName;
        this.enumName = enumName;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public String getEnumName() { return enumName; }
}
