package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Portuguese implements IfcMajors{
    @Override
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.PortugueseEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, studentYear, bSecondMajor);
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor) {
        List<String> removeList = new ArrayList<>();

        if (studentYear <= 2016) {
            removeList.addAll(Arrays.asList("A07136","A07146", "A07465", "A07138"));
        }
        if (studentYear <= 2015) {
            removeList.addAll(Arrays.asList("A07467", "A07466", "A07116"));
            if (bSecondMajor == true) {
                removeList.add("N07327");
            }
        }
        if (studentYear <= 2014) {
            removeList.addAll(Arrays.asList("A07317", "N07327"));
            if (bSecondMajor == true) {
                removeList.add("A07208");
            }
        }

        return GrdCondEct.removeCourseListByNumber(courseList, removeList);
    }


    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList) {
        return remainCourseList;
    }
}
