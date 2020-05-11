package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreditLibArtsField {


    public static List<String> makeFieldCreditByStudentYear(Integer studentYear) {
        List<String> grdLibArtsArea = new ArrayList<>();
        if (studentYear == 2015 || studentYear == 2016) {
            grdLibArtsArea.addAll(Arrays.asList("언어와문학", "문화와예술", "역사와철학", "인간과사회", "과학과기술"));
        } else if (studentYear == 2017) {
            grdLibArtsArea.addAll(Arrays.asList("언어와문학", "문화와예술", "역사와철학", "인간과사회", "과학과기술", "핵심인문기초"));
        }

        return grdLibArtsArea;
    }
}
