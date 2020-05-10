package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GrdCondEct {
    public static String getStudentYear(String studentNumber) {
        if (studentNumber.length() != 9) { return null; }
        return studentNumber.substring(0, 4);
    }

    public static Integer getInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("Error occured while parsing String into Integer");
            return getInteger(str);
        }
    }

    public static Boolean getStudentBool(String str) {
        if (str == null) { // or length == 0?
            return false;
        } else {
            return true;
        }
    }

    public static String getEngFromKorMajor(String korMajorName) {

        String engMajorName = Stream.of(departments.values())
                .filter(s -> korMajorName.equals(s.getKorName()))
                .map(s->s.getEngName())
                .findFirst()
                .orElse(null);

        return engMajorName;
    }

//    public static List<String> makeMajorCourseByName(String majorName) {
//        List<String> resultCourses = new ArrayList<String>();
//        String majorEnumName;
//
//        majorEnumName = Stream.of(departments.values())
//                .filter(s -> majorName.equals(s.name()))
//                .map(s -> s.getEnumName())
//                .findFirst()
//                .orElse(null);
//
//        if (majorEnumName != null) {
//            try {
//                Class<?> clz = Class.forName(majorEnumName);
//                Stream.of(clz.getEnumConstants())
//                        .forEach(s -> resultCourses.add(String.valueOf(s)));
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return resultCourses;
//    }
}
