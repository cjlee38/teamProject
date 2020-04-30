package com.hufsSchedule.hufsScheduleSystem.GrdCond;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.Majors;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.LibArts;


import java.util.ArrayList;

public class GrdCourseService {

    private String studentYear;
    private String firstMajorName, secondMajorName;
    private ArrayList<String> firstMajorCourses, secondMajorCourses, libArtsCourses;

    public GrdCourseService(String studentNumber, String firstMajorName, String secondMajorName) {
        this.studentYear = getStudentYear(studentNumber);
        this.firstMajorName = firstMajorName;
        this.secondMajorName = secondMajorName;

        this.firstMajorCourses = makeMajorCoursesByInfo(studentYear, firstMajorName, false);
        this.secondMajorCourses = makeMajorCoursesByInfo(studentYear, secondMajorName, true);
        this.libArtsCourses = makeLibartsCourseByInfo(studentYear, firstMajorName, secondMajorName);

    }

    public ArrayList<String> makeMajorCoursesByInfo(String studentYear, String majorName, boolean bSecondMajor) {
        Majors courseInstance;
        try {
            courseInstance = (Majors)Class.forName(majorName)
                    .getDeclaredConstructor(String.class, boolean.class)
                    .newInstance(studentYear, bSecondMajor);

        } catch(Exception e) {
            courseInstance = null;
            System.out.println("error occured");
        }

        ArrayList<String> courseList = courseInstance.getMajorCourseList();

        return courseList;
    }

    public ArrayList<String> makeLibartsCourseByInfo(String studentYear, String firstMajorName, String secondMajorName) {
        LibArts courseInstance;

        try {
            courseInstance = (LibArts)Class.forName("LibArts" + studentYear)
                    .getConstructor(String.class, String.class, String.class)
                    .newInstance(studentYear, firstMajorName, secondMajorName);
        } catch (Exception e) {
            courseInstance = null;
            System.out.println("Libarts init error occured");
        }

        ArrayList<String> courseList = courseInstance.getLibArtsCourseList();

        return courseList;


    }

    public String getStudentYear(String studentNumber) {
        if (studentNumber.length() != 9) { return null; }
        return studentNumber.substring(0, 4);
    }

    public ArrayList<String> getFirstMajorCourses() {
        return firstMajorCourses;
    }

    public ArrayList<String> getSecondMajorCourses() {
        return secondMajorCourses;
    }

    public ArrayList<String> getLibArtsCourses() {
        return libArtsCourses;
    }

}
