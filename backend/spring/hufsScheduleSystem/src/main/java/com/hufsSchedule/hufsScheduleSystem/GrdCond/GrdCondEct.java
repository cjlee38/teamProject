package com.hufsSchedule.hufsScheduleSystem.GrdCond;

public class GrdCondEct {
    public static String getStudentYear(String studentNumber) {
        if (studentNumber.length() != 9) { return null; }
        return studentNumber.substring(0, 4);
    }

    // public boolean getGrdForeignCertification() {};
}
