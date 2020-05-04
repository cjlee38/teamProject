package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import java.util.ArrayList;

public interface LibArts {
    public ArrayList<String> getLibArtsCourseList();
    public ArrayList<String> modifyCourseListByFirstMajor(ArrayList<String> courseList, String firstMajorName);
    public ArrayList<String> modifyCourseListBySecondMajor(ArrayList<String> courseList, String secondMajorName);
}
