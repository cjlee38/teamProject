package com.hufsSchedule.hufsScheduleSystem.GrdCond;

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
        A01740("Critical Writing(General 1)", "Critical Writing(General 1)"),
        A01741("Critical Writing(Honors 1)", "Critical Writing(Honors 1)"),
        A10104("Critical Writing(General 2)", "Critical Writing(General 2)"),
        A10105("Critical Writing(Honors 2)", "Critical Writing(Honors 2)");
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

        B06135("베트남역사1(1)", "History of Vietnamese 1(1)"),
        B06136("베트남역사1(2)", "History of Vietnamese 1(2)"),
        B06137("베트남역사2(1)", "History of Vietnamese 2(2)"),
        B06138("베트남역사2(2)", "History of Vietnamese 2(2)"), // 베트남역사 set

        B06466("베트남문학이해1", "Understanding Vietnamese Literature 1"),
        B06467("베트남문학이해2", "Understanding Vietnamese Literature 2"); // 베트남 문학이해 set. 문학이해2는 임시(20-2에 개설되는듯)


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

    enum PortugueseEnum implements CourseEnums {
        A07137("기초포르투갈어쓰기1", "Elementry Portuguese Composition 1"),
        A07134("포르투갈어문법1", "Portuguese Grammar 1"),
        A07115("초급포르투갈어회화(1)", "Elementry Portuguese Conversation (1)"),
        A07207("중급포르투갈어회화(1)", "Intermediate Portuguese Conversation (1)"),
        A07208("중급포르투갈어회화(2)", "Intermediate Portuguese Conversation (2)"), // 14

        A07317("고급포르투갈어회화작문(1)", "Advanced Portuguese Conversation & Composition (1)"),
        N07327("고급포르투갈어회화작문(2)", "Advanced Portuguese Conversation & Composition (2)"), // 15

        A07467("기초포르투갈어쓰기2", "Elementry Portuguese Composition 2"),
        A07466("포르투갈어문법2", "Portuguese Grammar 2"),
        A07116("초급포르투갈어회화(2)", "Elementry Portuguese Conversation (2)"), //16

        A07136("기초시청각포르투갈어1", "Elementry Portuguese (A/V) 1"),
        A07146("기초시청각포르투갈어2", "Elementry Portuguese (A/V) 2"),
        A07465("포.브라질지역입문","Introduction to Luso-Brazilian Area Studies"),
        A07138("루소폰아프리카지역입문", "Introduction to Lusophone African Area Studies"); // 17 ~

        final private String korName, engName;

        PortugueseEnum(String korName, String engName) {
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

    enum DutchEnum implements CourseEnums {
        A08129("네덜란드어회화작문 1 (1)", "Dutch Conversation & Composition 1 (1)"),
        A08130("네덜란드어회화작문 1 (2)", "Dutch Conversation & Composition 1 (2)"),
        A08131("네덜란드어회화작문 2 (1)", "Dutch Conversation & Composition 2 (1)"),
        A08132("네덜란드어회화작문 2 (2)", "Dutch Conversation & Composition 2 (2)"),
        A08382("멀티미디어네덜란드어 1 (1)", "Multimedia Dutch (1)"),
        A08383("멀티미디어네덜란드어 1 (2)", "Multimedia Dutch 1 (2)"),
        A08384("멀티미디어네덜란드어 2 (1)", "Multimedia Dutch 2 (1)"),
        A08485("멀티미디어네덜란드어 2 (2)", "Multimedia Dutch 2 (2)"),
        A08125("초급네덜란드어강독문법 (1)", "Elementry Dutch Readings & Grammer (1)"),
        A08126("초급네덜란드어강독문법 (2)", "Elementry Dutch Readings & Grammer (2)"),
        A08219("중급네덜란드어강독문법 (1)", "Intermediate Dutch Readings & Grammar (1)"),
        A08220("중급네덜란드어강독문법 (2)", "Intermediate Dutch Readings & Grammar (2)"),
        A08393("FLEX네덜란드어", "FLEX Dutch");


        final private String korName, engName;

        DutchEnum(String korName, String engName) {
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

    enum ScandinavianEnum implements CourseEnums {
        A09108("초급스웨덴어실습 (2)", "Elementry Swedish Language Lab. (2)"),
        A09107("초급스웨덴어실습 (1)", "Elementry Swedish Language Lab. (1)"),
        A09323("중급스웨덴어회화 (1)", "Intermediate Swedish Conversation (1)"),
        A09326("중급스웨덴어회화 (2)", "Intermediate Swedish Conversation (2)");

        final private String korName, engName;

        ScandinavianEnum(String korName, String engName) {
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

    enum MalayIndonesianEnum implements CourseEnums {
        B03110("초급마.인어회화작문 (1)", "Elementary Malay-Indonesian Conversation & Composition (1)"),
        O03115("초급마.인어회화작문 (2)", "Elementary Malay-Indonesian Conversation & Composition (2)"),
        B03206("중급마.인어회화작문 (1)", "Intermediate Malay-Indonesian Conversation & Composition (1)"),
        O03202("중급마.인어회화작문 (2)", "Intermediate Malay-Indonesian Conversation & Composition (2)"), // 14까지
        
        B03493("인도네시아세계역사.문화:인도네시아,동티모르,필리핀",
                "History and Culture of Indonesia World: Indonesia, East Timor, and Philippines"),
        B03469("말레이세계역사와문화:말레이시아,싱가포르,브루나이" ,
                "History and Culture of Malay World: Malaysia, Singapore, and Brunei"),
        B03109("초급마.인어 (1)", "Elementry Malay-Indonesian (1)"),
        B03229("초급마.인어 (2)", "Elementry Malay-Indonesian (2)"),
        B03111("영상마.인어 (1)", "Audio-Visaul Malay-Indonesian Language (1)"),
        B03126("영상마.인어 (2)", "Audio-Visaul Malay-Indonesian Language (2)"); // 15부터

        final private String korName, engName;

        MalayIndonesianEnum(String korName, String engName) {
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

    enum ArabicEnum implements CourseEnums {
        B04206("초급아랍어회화 (1)", "Elementry Arabic Conversation (1)"),
        B04225("초급아랍어회화 (2)", "Elementry Arabic Conversation (2)"),
        B04223("중급아랍어회화 (1)", "Intermediate Arabic Conversation (1)"),
        B04224("중급아랍어회화 (2)", "Intermediate Arabic Conversation (2)");

        final private String korName, engName;

        ArabicEnum(String korName, String engName) {
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

    // --------------------------- Liberal Arts Courses ---------------------------------- //

    enum LibArts2015Enum implements CourseEnums {
        Y12101("미네르바인문(1)읽기와쓰기", "Minerva Humanities Reading and Writing"),
        Y12102("미네르바인문(2)읽기와토의.토론", "Minerva Humanities Reading and Discussion & Debate"),
//        ("신입생세미나", "Seminar for Freshmen"),
        Y12104("HUFS Career Design 진로설정과취.창업경력개발", "HUFS Career Design (Defining Career Path & Development)"),
        FFFFFA("신입생세미나", "Seminar for Freshmen"),
        FFFFFB("교양외국어(1)", "Communicative Foreign Language"),
        FFFFFC("교양외국어(2)", "Communicative Foreign Language");

//        Y13101("교양외국어", "Communicative English 1"),
//        Y13102("교양영어2", "Communicative English 2"),
//        Y13103("교양프랑스어1", "Communicative French 1"),
//        Y13104("교양프랑스어2", "Communicative French 2"),
//        Y13105("교양독일어2", "Communicative German 1"),
//        Y13106("교양독일어2", "Communicative German 2"),
//        Y13107("교양노어2", "Communicative Russian 1"),
//        Y13108("교양노어2", "Communicative Russian 2"),
//        Y13109("교양스페인어1", "Communicative Spansish 1"),
//        Y13110("교양스페인어2", "Communicative Spansish 2"),
//        Y13111("교양이탈리아어1", "Communicative Spansish 1"),
//        Y13112("교양이탈리아어1", "Communicative Spansish 1"),
//        Y13113("교양포르투갈어1", "Communicative Spansish 1"),
//        Y13114("교양포트루갈어1", "Communicative Spansish 1"),
//        Y13115("교양중국어1", "Communicative Spansish 1"),
//        Y13115("교양중국어1", "Communicative Spansish 1"),


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

//    enum LibArts2014Enum implements CourseEnums {
//
//
//        final private String korName, engName;
//
//        LibArts2014Enum(String korName, String engName) {
//            this.korName = korName;
//            this.engName = engName;
//        }
//
//        @Override
//        public String getKorName() {
//            return korName;
//        }
//        @Override
//        public String getEngName() {
//            return engName;
//        }
//        @Override
//        public String getCourseNumber() {
//            return name();
//        }
//
//
//    }



}
