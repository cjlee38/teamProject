package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.Timetable;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.*;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggInstructionService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggRatioService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggTableService;
import java.util.*;

import static com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc.*;

public class SuggSysService {
    /*
    maxCredit -> 선택한 학점에서 과목 학점만큼 제거
    Table -> 선택한 과목 + 공강시간 process
    validCourseList -> 20-1 모든 과목에서 내가 수강한 과목  & 선택한 과목 제거. ( + 본인 전공 과목만 남길것인지? )
    creditRatio -> 지금까지 수강한 과목 학점 비율. & 1전공-전공명 / 이중전공 - 전공명 map
     */
    public static SuggSysObj initSuggSys(User userInfo, UserSelectsObj userSelectsObj, List<Instruction> userTakenCourses, Credit userCredit, List<Instruction> entireCourses) {
        CreditRange creditRange = SuggCreditService.initTimeTableCredit(userSelectsObj.getUserSelectCredit(), userSelectsObj.getUserSelectCourses());
        Table<String, String, WeightInstruction> timeTable = SuggTableService.initTimeTable(userSelectsObj.getUserSelectCourses(), userSelectsObj.getUserSelectFreeTime());
        List<WeightInstruction> validInstructions = SuggInstructionService.initValidInstructions(entireCourses, userTakenCourses, userSelectsObj.getUserSelectCourses(), userInfo);
        CreditRatio creditRatio = SuggRatioService.initCreditRatio(userCredit, userInfo, creditRange);

        return new SuggSysObj(creditRange, timeTable, validInstructions, creditRatio);
    }

    // 강의과목을 테이블에 추가
    public static List<Table<String, String, WeightInstruction>> generateTimeTable(SuggSysObj suggSysObj, Map<String, List<CourseEnums>> remainCourses,
                                                                                   User userInfo, List<List<TimetableDto.findInstructionCode>> dataset) {
        System.out.println("valid instructions length : " + suggSysObj.getValidInstructions().size());
        SuggInstructionService.tuneInstructionWeights(suggSysObj.getValidInstructions(), remainCourses, userInfo, dataset); // void
        List<WeightInstruction> sorted = sortInstructionByWeight(suggSysObj.getValidInstructions());

        List<Table<String, String, WeightInstruction>> tableList = new ArrayList<>();
        for (Integer idx = 0; idx < sorted.size(); idx++) {
            Table<String, String, WeightInstruction> table = suggSysObj.getTimeTable();

            List<WeightInstruction> Instructions = SuggSysFunc.copyInstructions(sorted);
            Integer maxCredit = suggSysObj.getCreditRange().getMaxCredit();
            CreditRatio ratio = SuggSysFunc.copyCreditRatio(suggSysObj.getCreditRatio());

            backtracking(Instructions, table, maxCredit, ratio, tableList, idx);
        }

        for (Table<String, String, WeightInstruction> table : tableList) {
            Integer summed = SuggSysFunc.sumTableCredit(table);
            if (summed <= suggSysObj.getCreditRange().getMaxCredit() - 2) {
                for (WeightInstruction weightInstruction : sorted) {
                    SuggTableService.inputInstructionToTable(table, weightInstruction);
                }
            }
        }

        return tableList;
    }

    public static boolean isPossible(Integer credit) {
        if (credit <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Integer backtracking(List<WeightInstruction> instructions, Table<String, String, WeightInstruction> table,
                             Integer credit, CreditRatio ratio, List<Table<String, String, WeightInstruction>> tableList, Integer idx) {
        if (!isPossible(credit)) {
            tableList.add(table);
            return 0;
        }
        if (tableList.size() > 30) {
            return 0;
        }

        WeightInstruction currentInstruction = instructions.get(idx);
        Table<String, String, WeightInstruction> currentTable = copyTable(table);
        CreditRatio currentRatio = copyCreditRatio(ratio);

        Boolean inputFlag = SuggTableService.inputInstructionToTable(currentTable, currentInstruction, ratio);
        if (inputFlag == true) {
            credit -= currentInstruction.getInstruction().getCredit();
            SuggRatioService.subtractRatio(currentRatio, currentInstruction);
        }

        idx++;

        for (Integer iter = idx; iter < instructions.size(); iter++) {
            Integer flag = backtracking(instructions, currentTable,
                    credit, currentRatio, tableList, iter);
            if (flag == 0) { break; }
        }

        return 0;
    }

    public static TimetableDto.Result cvtTableToResult(Table<String, String, WeightInstruction> table) {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");

        ArrayList<TimetableDto.Day> days = new ArrayList<>();
        for (String column : columns) {
            ArrayList<TimetableDto.Cell> cells = new ArrayList<>();
            for (String row : rows) {
                WeightInstruction cell = table.get(row, column);
                if (cell != null && ! isInstructionEmpty(cell)) {
                    String instruction = cell.getInstruction().getInstructionNumber();
                    String instructor = cell.getInstruction().getProfessor();
                    Integer required = 0;
                    if (cell.getInstruction().isRequired() == true) {
                        required = 1;
                    }
                    TimetableDto.Data data = new TimetableDto.Data(instruction, instructor, required);
                    cells.add(new TimetableDto.Cell(data));
                } else {
                    cells.add(new TimetableDto.Cell(false));
                }
            days.add(new TimetableDto.Day(cells));
            }
        }
        TimetableDto.Result result = new TimetableDto.Result(days);
        return result;
    }

//    public void apriori() {
//        double minSupport = 0.5;
//        Apriori<Type>
//        Apriori apriori = new Apriori.Builder<>(minSupport).create();
//        Iterable<Transaction>
////        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(minSupport).create();
////        RuleSet
//    }
}

