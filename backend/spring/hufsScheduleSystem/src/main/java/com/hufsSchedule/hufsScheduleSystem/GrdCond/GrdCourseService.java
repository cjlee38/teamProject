package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;


import java.util.ArrayList;
import java.util.List;

public class GrdCourseService {

    private String studentYear;
    private List<String> firstMajorCourses, secondMajorCourses, libArtsCourses;

    public GrdCourseService(String studentNumber, String firstMajorName, String secondMajorName) {
        this.studentYear = getStudentYear(studentNumber);

        this.firstMajorCourses = makeMajorCoursesByInfo(studentYear, firstMajorName, false);
        this.secondMajorCourses = makeMajorCoursesByInfo(studentYear, secondMajorName, true);
        this.libArtsCourses = makeLibArtsCourseByInfo(studentYear, firstMajorName, secondMajorName);

    }

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

    public String getStudentYear(String studentNumber) {
        if (studentNumber.length() != 9) { return null; }
        return studentNumber.substring(0, 4);
    }

    public List<String> getAllGrdCourseList() {
        List<String> allCourses = new ArrayList<String>();

        allCourses.addAll(this.getFirstMajorCourses());
        allCourses.addAll(this.getSecondMajorCourses());
        allCourses.addAll(this.getLibArtsCourses());

        return allCourses;
    }

    public List<String> getFirstMajorCourses() {
        return firstMajorCourses;
    }

    public List<String> getSecondMajorCourses() {
        return secondMajorCourses;
    }

    public List<String> getLibArtsCourses() {
        return libArtsCourses;
    }

}
