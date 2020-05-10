package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;


import java.util.ArrayList;
import java.util.List;

public class GrdCourseService {

    public static List<CourseEnums> makeMajorCoursesByInfo(String studentYear, String majorName, Boolean bSecondMajor) {
        IfcMajors courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond."+majorName;
        try {
            courseInstance = (IfcMajors)Class.forName(className)
                    .newInstance();

        } catch(Exception e) {
            courseInstance = null;
            System.out.println(e);
//            System.out.println("error in making major courses occured");

        }

        List<CourseEnums> courseList = courseInstance.makeMajorCourseList(studentYear, bSecondMajor);

        return courseList;
    }

    public static List<CourseEnums> makeLibArtsCourseByInfo(String studentYear, String firstMajorName, String secondMajorName) {
        IfcLibArts courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.LibArts" + studentYear;

        try {
            courseInstance = (IfcLibArts)Class.forName(className)
                    .newInstance();
        } catch (Exception e) {
            courseInstance = null;
            System.out.println("Libarts init error occured");
        }

        List<CourseEnums> courseList = courseInstance.makeLibArtsCourseList(firstMajorName, secondMajorName);

        return courseList;


    }

    public static List<CourseEnums> makeAllGrdCourseList(String studentYear, String firstMajorName, String secondMajorName) {
        List<CourseEnums> courseList = new ArrayList<CourseEnums>();

        courseList.addAll(makeMajorCoursesByInfo(studentYear, firstMajorName, false));
        courseList.addAll(makeMajorCoursesByInfo(studentYear, secondMajorName, true));
        courseList.addAll(makeLibArtsCourseByInfo(studentYear, firstMajorName, secondMajorName));

        return courseList;
    }



}
