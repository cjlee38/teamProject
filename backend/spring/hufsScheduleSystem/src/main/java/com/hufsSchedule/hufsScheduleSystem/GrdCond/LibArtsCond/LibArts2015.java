package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibArts2015 implements IfcLibArts {
    enum LibArts2015Enum {
        Y12101("미네르바인문(1)읽기와쓰기", "Minerva Humanities Reading and Writing"),
        Y12102("미네르바인문(2)읽기와토의.토론", "Minerva Humanities Reading and Discussion & Debate"),
        U7618("신입생세미나", "Seminar for Freshmen"),
        Y12104("HUFS Career Design 진로설정과취.창업경력개발", "HUFS Career Design (Defining Career Path & Development)");

        final private String korName, engName;

        LibArts2015Enum(String korName, String engName) {
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


    private List<String> libArtsCourseList;


    public LibArts2015(String studetYear, String firstMajorName, String secondMajorName) {
        this.libArtsCourseList = makeLibArtsCourseList(firstMajorName, secondMajorName);
    }


    @Override
    public List<String> makeLibArtsCourseList(String firstMajorName, String secondMajorName) {
        List<String> baseCourseList = new ArrayList<String>();
        Arrays.asList(LibArts2015Enum.values()).forEach(e -> baseCourseList.add(e.name()));

        List<String> retCourseList =
                modifyCourseListBySecondMajor(
                        modifyCourseListByFirstMajor(baseCourseList, firstMajorName),
                        secondMajorName);
        return retCourseList;
    }

    // need to fixed
    @Override
    public List<String> modifyCourseListByFirstMajor(List<String> courseList, String firstMajorName) {
        return courseList;
    }

    @Override
    public List<String> modifyCourseListBySecondMajor(List<String> courseList, String secondMajorName) {
        return courseList;
    }

    @Override
    public List<String> getLibArtsCourseList() {
        return libArtsCourseList;
    }


}
