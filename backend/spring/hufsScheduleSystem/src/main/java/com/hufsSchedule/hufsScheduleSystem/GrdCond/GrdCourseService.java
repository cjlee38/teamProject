package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;


import java.util.ArrayList;
import java.util.List;

public class GrdCourseService {

    public static IfcMajors makeMajorObjsByInfo(String majorName) {
        IfcMajors courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond."+majorName;
        try {
            courseInstance = (IfcMajors)Class.forName(className)
                    .newInstance();

        } catch(Exception e) {
            courseInstance = null;
            System.out.println(e);
            System.out.println("error in making major courses occured");
        }


        return courseInstance;
    }

    public static List<CourseEnums> makeMajorCoursesByInfo(Integer studentYear, String majorName, Boolean bSecondMajor) {
        IfcMajors majorObj = makeMajorObjsByInfo(majorName);
        List<CourseEnums> courseList = majorObj.makeMajorCourseList(studentYear, bSecondMajor);

        return courseList;
    }

    public static List<CourseEnums> makeLibArtsCourseByInfo(Integer studentYear, String firstMajorName, String secondMajorName) {
        IfcLibArts courseInstance;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.LibArts" + studentYear.toString();

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

    public static List<CourseEnums> makeAllGrdCourseList(Integer studentYear, String firstMajorName, String secondMajorName) {
        List<CourseEnums> courseList = new ArrayList<>();

        courseList.addAll(makeMajorCoursesByInfo(studentYear, firstMajorName, false));
        for (CourseEnums c : courseList) {
            System.out.println(c.getKorName());
        }

        if (secondMajorName != null) {
            System.out.println(secondMajorName);
            courseList.addAll(makeMajorCoursesByInfo(studentYear, secondMajorName, true));
            for (CourseEnums c : courseList) {
                System.out.println(c.getKorName());
            }

        }
        courseList.addAll(makeLibArtsCourseByInfo(studentYear, firstMajorName, secondMajorName));

        for (CourseEnums c : courseList) {
            System.out.println(c.getKorName());
        }
        return courseList;
    }



}
