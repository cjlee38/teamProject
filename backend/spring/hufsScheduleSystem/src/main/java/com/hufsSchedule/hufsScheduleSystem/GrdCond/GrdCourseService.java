package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;


import java.util.ArrayList;
import java.util.List;

public class GrdCourseService {

    public List<String> makeMajorCoursesByInfo(String studentYear, String majorName, Boolean bSecondMajor) {
        IfcMajors courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond."+majorName;
        try {
            courseInstance = (IfcMajors)Class.forName(className)
                    .getDeclaredConstructor(String.class, Boolean.class)
                    .newInstance(studentYear, bSecondMajor);

        } catch(Exception e) {
            courseInstance = null;
            System.out.println("error in making major courses occured");
        }

        List<String> courseList = courseInstance.getMajorCourseList();

        return courseList;
    }

    public List<String> makeLibArtsCourseByInfo(String studentYear, String firstMajorName, String secondMajorName) {
        IfcLibArts courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.LibArts" + studentYear;

        try {
            courseInstance = (IfcLibArts)Class.forName(className)
                    .getConstructor(String.class, String.class, String.class)
                    .newInstance(studentYear, firstMajorName, secondMajorName);
        } catch (Exception e) {
            courseInstance = null;
            System.out.println("Libarts init error occured");
        }

        List<String> courseList = courseInstance.getLibArtsCourseList();

        return courseList;


    }

    public List<String> makeAllGrdCourseList(String studentYear, String firstMajorName, String secondMajorName) {
        List<String> courseList = new ArrayList<String>();

        courseList.addAll(makeMajorCoursesByInfo(studentYear, firstMajorName, false));
        courseList.addAll(makeMajorCoursesByInfo(studentYear, secondMajorName, true));
        courseList.addAll(makeLibArtsCourseByInfo(studentYear, firstMajorName, secondMajorName));

        return courseList;
    }



}
