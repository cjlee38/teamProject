package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import java.util.ArrayList;
import java.util.List;

public interface IfcLibArts {
    public List<String> makeLibArtsCourseList(String firstMajorName, String secondMajorName);
    public List<String> modifyCourseListByFirstMajor(List<String> courseList, String firstMajorName);
    public List<String> modifyCourseListBySecondMajor(List<String> courseList, String secondMajorName);
    public List<String> getLibArtsCourseList();
}
