package com.hufsSchedule.hufsScheduleSystem.GrdCond;

public enum departments {
    ENGLISH1("영어학", "ELTT"),
    ENGLISH2("영미문학.문화학", "EnglishLitAndCult"),
    ENGLISH3("영어통번역학", "English"), //////
    ENGLISH4("EICC학", "EICC"),
    WESTERN1("프랑스어", "French"),
    WESTERN2("프랑스응용어문학","AppliedFrench"),
    WESTERN3("FATI", "FATI"),
    WESTERN4("프랑스,EU","FrenchEU"),
    WESTERN5("독일어","German"),
    WESTERN6("노어", "Russian"),
    WESTERN7("이탈리아어", "Italian"),
    WESTERN8("포르투갈어", "Portuguese"),
    WESTERN9("네덜란드어", "Dutch"),
    WESTERN10("스칸디나비아어","Scandinavian"),
    WSTERN11("스페인어", "Spanish"),

    ASIA1("마인어", "MalayIndonesian"),
    ASIA2("아랍어", "Arabic"),
    ASIA3("태국어", "Thai"),
    ASIA4("베트남어", "Vietnamese"),
    ASIA5("인도어", "Hindi"),
    ASIA6("터키,아제르바이잔어","TurkishAzerbaijani"),
    ASIA7("이란어","Persian"),
    ASIA8("몽골어","Mongolian"),

    CHINESE1("중국언어문화학부", "ChinenseLanguage"),
    CHINESE2("중국외교통상학부", "ChinenseDiplomacy"),

    JAPANESE1("일본언어문화학부", "JapneseLanguage"),
    JAPANESE2("융합일본지역학부", "JapaneseIntegrated"),

    SOCIALSCIENCE1("정치외교학", "PoliticsDiplomacy"),
    SOCIALSCIENCE2("행정학", "Administration"),
    SOCIALSCIENCE3("미디어커뮤니케이션학부", "MediaCommunication"),
    SOCIALSCIENCE4("광고.PR.브랜딩","AdPRBranding"),
    SOCIALSCIENCE5("방송.영상.뉴미디어", "BroadCastingFilmNewmedia"),
    SOCIALSCIENCE6("언론정보","JournalismInformation"),

    BUSINESSADMINISTRATION1("국제통상학","InternationalEconomicsAndLaw"),
    BUSINESSADMINISTRATION2("경제학부", "Economics"),

    BUSINESS("경영학", "Business"),	
    EDUCATION1("영어교육", "EnglishEducation"),
    EDUCATION2("프랑스어교육", "FrenchEducation"),
    EDUCATION3("독일어교육", "GermanEducation"),
    EDUCATION4("한국어교육", "KoreanEducation"),
    EDUCATION5("중국어교육", "ChineseEducation"),
    EDUCATION6("교육학전공", "Education"),
    EDUCATION7("체육학전공", "something"), //

    INTERNATIONAL("국제학부", "InternationalStudies"),

    LD1("LD전공", "LD"),
    LD2("사회과학전공", "something"), //

    LT1("LT학부", "LT"),

    CONVERGENCE1("융복합소프트웨어", "SoftwareConvergence"),
    CONVERGENCE2("EU전공", "EU"),
    CONVERGENCE3("브릭스", "BRICs"),
    CONVERGENCE4("국가리더", "NationalLeader"),
    CONVERGENCE5("동북아외교통상", "NEAsianDiplomacy"),
    CONVERGENCE6("디지털인문한국학","DigitalKoreanHumanities"),
    CONVERGENCE7("문화콘텐츠학","CulturalContents"),
    CONVERGENCE8("세계문화예술경영","ArtsCulturalMediation"),
    CONVERGENCE9("언어와공학","LanguageTechnology");

    final private String korName, engName;

    departments(String korName, String engName) {
        this.korName = korName;
        this.engName = engName;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }
}
