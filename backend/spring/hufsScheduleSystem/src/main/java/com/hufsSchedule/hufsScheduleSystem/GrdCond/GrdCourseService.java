package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static IfcLibArts makeLibArtsObjsByInfo(Integer studentYear) {
        IfcLibArts courseInstance;

        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.LibArts" + studentYear.toString();

        try {
            courseInstance = (IfcLibArts)Class.forName(className)
                    .newInstance();
        } catch (Exception e) {
            courseInstance = null;
            System.out.println("Libarts init error occured");
        }

        return courseInstance;
    }

    public static List<CourseEnums> makeLibArtsCourseByInfo(Integer studentYear, String firstMajorName, String secondMajorName) {
        IfcLibArts libArtsObj = makeLibArtsObjsByInfo(studentYear);
        List<CourseEnums> courseList =  libArtsObj.makeLibArtsCourseList(firstMajorName, secondMajorName);

        return courseList;
    }

    public static Map<String, List<CourseEnums>> makeAllGrdCourseList(Integer studentYear, String firstMajorName, String secondMajorName) {
        Map<String, List<CourseEnums>> courseMap = new HashMap<>();

        courseMap.put("firstMajor", makeMajorCoursesByInfo(studentYear, firstMajorName, false));

        if (secondMajorName != null) {
            courseMap.put("secondMajor", makeMajorCoursesByInfo(studentYear, secondMajorName, true));
        } else {
            List<CourseEnums> empty = new ArrayList<>();
            courseMap.put("secondMajor", empty);
        }
        courseMap.put("liberalArts", makeLibArtsCourseByInfo(studentYear, firstMajorName, secondMajorName));

        return courseMap;
    }



}
