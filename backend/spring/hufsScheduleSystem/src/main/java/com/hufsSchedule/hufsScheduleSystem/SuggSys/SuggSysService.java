package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
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
        List<WeightInstruction> validInstructions = SuggInstructionService.initValidInstructions(entireCourses, userTakenCourses, userSelectsObj.getUserSelectCourses(), userSelectsObj.getUserAbandonCourses(), userInfo);
        CreditRatio creditRatio = SuggRatioService.initCreditRatio(userCredit, userInfo, creditRange);

        return new SuggSysObj(creditRange, timeTable, validInstructions, creditRatio);
    }

    // 강의과목을 테이블에 추가
    public static List<Table<String, String, WeightInstruction>> generateTimeTable(SuggSysObj suggSysObj, Map<String, List<CourseEnums>> remainCourses,
                                                                                   User userInfo, List<List<TimetableDto.findInstructionCode>> dataset) {
        System.out.println("valid instructions length : " + suggSysObj.getValidInstructions().size());
        SuggInstructionService.tuneInstructionWeights(suggSysObj.getValidInstructions(), remainCourses, userInfo, dataset); // void
        List<WeightInstruction> sorted = sortInstructionByWeight(suggSysObj.getValidInstructions());

        System.out.println("----------------------------");
        for (WeightInstruction inst : suggSysObj.getValidInstructions()) {
            System.out.println(inst.getInstruction().getSubject() + inst.getWeight());
        }
//        for (Integer idx = 0; idx <10; idx++) {
//            System.out.println(sorted.get(idx).getInstruction().getSubject());
//            System.out.println(sorted.get(idx).getInstruction().getInstructionNumber());
//            System.out.println(sorted.get(idx).getWeight());
//        }
        System.out.println("-------------------------------");
//        System.out.println(suggSysObj.getCreditRatio().getFieldToMajor());



        int limitIndex = 0;
        for (WeightInstruction i : suggSysObj.getValidInstructions()) {
            if (i.getWeight().equals(new Float(0.0))) {
                break;
            }
            limitIndex++;
        }
        if (limitIndex == 0) {
            limitIndex = 1;
        }

        System.out.println("sorted size : " + sorted.size());
        System.out.println("backtracking starts : " + limitIndex);
        int tableLimit = 10000/limitIndex;
        List<Table<String, String, WeightInstruction>> entireTableList = new ArrayList<>();
        for (Integer idx = 0; idx < limitIndex; idx++) {
            List<Table<String, String, WeightInstruction>> tableList = new ArrayList<>();
            Table<String, String, WeightInstruction> table = suggSysObj.getTimeTable();

            Integer maxCredit = suggSysObj.getCreditRange().getMaxCredit();
            CreditRatio ratio = SuggSysFunc.copyCreditRatio(suggSysObj.getCreditRatio());

            backtracking(suggSysObj.getValidInstructions(), table, maxCredit, ratio, tableList, idx, tableLimit);
            entireTableList.addAll(tableList);
        }

        if (entireTableList.size() == 0) {
            entireTableList.add(suggSysObj.getTimeTable());
        }
        System.out.println("after backtracking : " + entireTableList.size());

//        for (Table<String, String, WeightInstruction> table : tableList) {
//            Integer summed = SuggSysFunc.sumTableCredit(table);
//            if (summed <= suggSysObj.getCreditRange().getMaxCredit() - 2) {
//                for (WeightInstruction weightInstruction : sorted) {
//                    SuggTableService.inputInstructionToTable(table, weightInstruction);
//                }
//            }
//        }

//        System.out.println("table list size : " + tableList.size());

        return entireTableList;
    }

    public static boolean isPossible(Integer credit) {
        if (credit <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Integer backtracking(List<WeightInstruction> instructions, Table<String, String, WeightInstruction> table,
                             Integer credit, CreditRatio ratio, List<Table<String, String, WeightInstruction>> tableList, Integer idx, Integer tableLimit) {
        if (!isPossible(credit)) {
            tableList.add(table);
            return 0;
        }
        if (tableList.size() > tableLimit) {
            return -1;
        }
        WeightInstruction currentInstruction = instructions.get(idx);
        Table<String, String, WeightInstruction> currentTable = copyTable(table);
        CreditRatio currentRatio = copyCreditRatio(ratio);

        Boolean inputFlag = SuggTableService.inputInstructionToTable(currentTable, currentInstruction, currentRatio);
        if (inputFlag == true) {
            credit -= currentInstruction.getInstruction().getCredit();
            SuggRatioService.subtractRatio(currentRatio, currentInstruction);
        }

        if (!isPossible(credit)) {
            tableList.add(table);
            return 1;
        }

        idx++;

        if (idx >= instructions.size()) {
            tableList.add(currentTable);
            return 0;
        } else {
            for (Integer iter = idx; iter < instructions.size(); iter++) {
                Integer flag = backtracking(instructions, currentTable, credit, currentRatio, tableList, iter, tableLimit);
                if (flag == 0) { break; }
                if (flag == -1) { return -1; }
            }
        }
        return 0;
    }

//    public static TimetableDto.Result cvtTableToResult(Table<String, String, WeightInstruction> table) {
//        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
//        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
//
//        ArrayList<TimetableDto.Day> days = new ArrayList<>();
//        for (String column : columns) {
//            ArrayList<TimetableDto.Cell> cells = new ArrayList<>();
//            for (String row : rows) {
//                WeightInstruction cell = table.get(row, column);
//                if (cell != null && ! isInstructionEmpty(cell)) {
//                    String instruction = cell.getInstruction().getInstructionNumber();
//                    String instructor = cell.getInstruction().getProfessor();
//                    Integer required = 0;
//                    if (cell.getInstruction().isRequired() == true) {
//                        required = 1;
//                    }
//                    TimetableDto.Data data = new TimetableDto.Data(instruction, instructor, required);
//                    cells.add(new TimetableDto.Cell(data));
//                } else {
//                    cells.add(new TimetableDto.Cell(false));
//                }
//            days.add(new TimetableDto.Day(cells));
//            }
//        }
//        TimetableDto.Result result = new TimetableDto.Result(days);
//        return result;
//    }

    public static ArrayList<Instruction> cvtTableToResult(Table<String, String, WeightInstruction> table) {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12", "13");

        Set<Instruction> set = new HashSet<>();
        for (String column : columns) {
            for (String row : rows) {
                WeightInstruction cell = table.get(row, column);
                if (cell != null && ! isInstructionEmpty(cell)) {
                    set.add(cell.getInstruction());
                }
            }
        }

        ArrayList<Instruction> ret = new ArrayList<>();
        ret.addAll(set);
        return ret;

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

