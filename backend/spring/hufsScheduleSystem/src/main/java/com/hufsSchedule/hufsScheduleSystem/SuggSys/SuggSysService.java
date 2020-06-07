package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.Timetable;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.*;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggInstructionService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggRatioService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggTableService;

import javax.xml.crypto.Data;
import java.util.*;

import static com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc.*;

public class SuggSysService {
    SuggCreditService suggCreditService;
    SuggTableService suggTableService;
    SuggInstructionService suggInstructionService;
    SuggRatioService suggRatioService;
    /*
    maxCredit -> 선택한 학점에서 과목 학점만큼 제거
    Table -> 선택한 과목 + 공강시간 process
    validCourseList -> 20-1 모든 과목에서 내가 수강한 과목  & 선택한 과목 제거. ( + 본인 전공 과목만 남길것인지? )
    creditRatio -> 지금까지 수강한 과목 학점 비율. & 1전공-전공명 / 이중전공 - 전공명 map
     */
    public SuggSysObj initSuggSys(User userInfo, UserSelectsObj userSelectsObj, Map<String, List<Instruction>> userTakenCourses, Credit userCredit, List<Instruction> entireCourses) {
        CreditRange creditRange = suggCreditService.initTimeTableCredit(userSelectsObj.getUserSelectCredit(), userSelectsObj.getUserSelectCourses());
        Table<String, String, WeightInstruction> timeTable = suggTableService.initTimeTable(userSelectsObj.getUserSelectCourses(), userSelectsObj.getUserSelectFreeTime());
        List<WeightInstruction> validInstructions = suggInstructionService.initValidInstructions(entireCourses, userTakenCourses, userSelectsObj.getUserSelectCourses(), userInfo);
        CreditRatio creditRatio = suggRatioService.initCreditRatio(userCredit, userInfo, creditRange);

        return new SuggSysObj(creditRange, timeTable, validInstructions, creditRatio);
    }

    // 전필을 강의과목에 추가
    public List<Table<String, String, WeightInstruction>> addInstructionsToTable(SuggSysObj suggSysObj, Map<String, List<CourseEnums>> remainCourses) {
        List<WeightInstruction> weightedInstructions = sortInstructionByWeight(suggInstructionService.addWeigthToNcssInstructions(remainCourses, suggSysObj.getValidInstructions())); // 남아있는 필수과목에 해당하는 Instruction만 남김

        List<Table<String, String, WeightInstruction>> tableList = new ArrayList<>();
        for (Integer idx = 0; idx <= weightedInstructions.size(); idx++) {
            Table<String, String, WeightInstruction> table = suggTableService.getEmptyTimeTable();

            List<WeightInstruction> Instructions = SuggSysFunc.copyInstructions(weightedInstructions);
            Integer maxCredit = suggSysObj.getCreditRange().getMaxCredit();
            CreditRatio ratio = SuggSysFunc.copyCreditRatio(suggSysObj.getCreditRatio());

            backtracking(Instructions, table, maxCredit, ratio, tableList, idx);
        }

        for (Table<String, String, WeightInstruction> table : tableList) {
            Integer summed = SuggSysFunc.sumTableCredit(table);
            if (summed <= suggSysObj.getCreditRange().getMaxCredit() - 2) {
                for (WeightInstruction weightInstruction : weightedInstructions) {
                    suggTableService.inputInstructionToTable(table, weightInstruction);
                }
            }
        }

        return tableList;
    }

    public boolean isPossible(Integer credit) {
        if (credit <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void backtracking(List<WeightInstruction> instructions, Table<String, String, WeightInstruction> table,
                             Integer credit, CreditRatio ratio, List<Table<String, String, WeightInstruction>> tableList, Integer idx) {
        if (!isPossible(credit)) {
            tableList.add(table);
            return;
        }

        WeightInstruction currentInstruction = instructions.get(idx);
        Table<String, String, WeightInstruction> currentTable = copyTable(table);
        CreditRatio currentRatio = copyCreditRatio(ratio);

        suggTableService.inputInstructionToTable(currentTable, currentInstruction, ratio);
        suggRatioService.subtractRatio(currentRatio, currentInstruction);
        idx++;

        for (Integer iter = 0; iter <= instructions.size(); iter++) {
            backtracking(instructions, currentTable,
                    credit, currentRatio, tableList, idx);
        }
    }

    public TimetableDto.Result cvtTableToResult(Table<String, String, WeightInstruction> table) {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");

        List<TimetableDto.Day> days = new ArrayList<>();
        for (String column : columns) {
            ArrayList<TimetableDto.Cell> cells = new ArrayList<>();
            for (String row : rows) {
                WeightInstruction cell = table.get(row, column);
                if (cell != null && isInstructionEmpty(cell)) {
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
}