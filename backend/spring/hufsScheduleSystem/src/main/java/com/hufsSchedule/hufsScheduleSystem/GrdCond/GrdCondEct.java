package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public static List<CourseEnums> removeCourseListByNumber(List<CourseEnums> courseList, List<String> removeList) {
        List<CourseEnums> resultCourseList;

        resultCourseList = courseList.stream()
                .filter(x -> !removeList.contains(x.getCourseNumber()))
                .collect(Collectors.toList());

        return resultCourseList;
    }

    public static List<String> extractCourseNumber(List<Instruction> userInstructions) {
        List<String> courseNumbers = new ArrayList<String>();
        userInstructions.stream().forEach(i -> courseNumbers.add(i.getInstructionNumber().substring(0,6)));

        return courseNumbers;
    }

    public static Integer extractUserFieldCredit(Integer studentYear, List<Instruction> userInstructions) {
        Integer userFieldCredit = 0;
        List<String> libArtsArea = new ArrayList<>();
        if (studentYear == 2015 || studentYear == 2016) {
            libArtsArea.addAll(Arrays.asList("언어와문학", "문화와예술", "역사와철학", "인간과사회", "과학과기술"));
        } else if (studentYear == 2017) {
            libArtsArea.addAll(Arrays.asList("언어와문학", "문화와예술", "역사와철학", "인간과사회", "과학과기술", "핵심인문기초"));
        }

        List<String> userAreas = new ArrayList<>();
        userInstructions.stream().forEach(x -> userAreas.add(x.getArea()));

        List<String> userUniqueAreas = new ArrayList<String>();
        userAreas.stream().distinct().forEach(x -> userUniqueAreas.add(x));

        for (String s : userUniqueAreas) {
            if (libArtsArea.contains(s)) {
                userFieldCredit++;
            }
        }

        return userFieldCredit;
    }
}
