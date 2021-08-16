package com.hufsSchedule.hufsScheduleSystem.Service;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Timetable;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCompareService;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondService;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.InstructionRepository;
import com.hufsSchedule.hufsScheduleSystem.Repository.TimeTableRepository;
import com.hufsSchedule.hufsScheduleSystem.Repository.TimeTableRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.SuggSysObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.UserSelectsObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggTableService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.UserSelectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeTimeTableService {
    private final CourseRepositorySupport courseRepositorySupport;
    private final ConditionCheckService conditionCheckService;
    private final InstructionRepository instructionRepository;
    private final UserRepository userRepository;
    private final TimeTableRepositorySupport timeTableRepositorySupport;
    private final TimeTableRepository timeTableRepository;
    private GrdCondService grdCondService;
    private GrdCompareService grdCompareService;
    private TimetableDto.Res res;
    private SuggSysService suggSysService;
    private UserSelectsService userSelectsService;

    public TimetableDto.Result checkCondition(TimetableDto.Req req){
        ConditionDto.courseInstructionRes condition = conditionCheckService.checkConditionForTimeTable(req.getUserId());
        ArrayList<Instruction> instructions = instructionRepository.findAllByYear(20L); //20년도 강의목록입니다.
//        List<TimetableDto.findInstructionCode> list = courseRepositorySupport.findInstructionCodeByMajor();

        Student studentInfo = userRepository.findById(req.getUserId()).orElseThrow(UserNotFoundException::new);
        List<Instruction> userTakenCourses = condition.getInstructions();
        System.out.println("userTakenCourses size : "+userTakenCourses.size());
        Credit userCredit = condition.getCredit();

        UserSelectsObj userSelectsObj = UserSelectsService.initUserSelects(req.getMyCourse(), req.getDeletedCourse(), req.getMyCredit(), req.getMyFreetime());
        SuggSysObj suggSysObj = suggSysService.initSuggSys(studentInfo, userSelectsObj, userTakenCourses, userCredit, instructions);
        GrdCondObj GrdObj = GrdCondService.makeGrdCondByUserInfo(studentInfo);
        GrdCondObj remainObj = GrdCompareService.compareGrdAndUser(studentInfo, condition.getInstructions(), userCredit, GrdObj);

        List<String> userArea = SuggSysFunc.getUserArea(studentInfo);
        List<List<TimetableDto.findInstructionCode>> dataset = new ArrayList<>();

        for (String area : userArea) {
            List<TimetableDto.findInstructionCode> data = courseRepositorySupport.findInstructionCodeByMajor(area);
            dataset.add(data);
            if (area.equals("교양")) {
                System.out.println("교양 data" + data);
            }
        }

        List<Table<String, String, WeightInstruction>> tables = SuggTableService.getTopNTableResult(
                SuggSysService.generateTimeTable(suggSysObj, remainObj.getGrdCourse(), studentInfo, dataset), 5
        );
        System.out.println(tables.size());

        ArrayList<ArrayList<Instruction>> results = new ArrayList<>();
        for (Table<String, String, WeightInstruction> table : tables) {
            results.add(SuggSysService.cvtTableToResult(table));
        }

        System.out.println(results.size());

        TimetableDto.Result realResult = new TimetableDto.Result(results);


        return realResult;

    }
    public TimetableDto.MyTimeTable checkTimeTable(Long userId){
        List<Instruction> instructionList = timeTableRepositorySupport.findInstructionByUser(userId);
        for (Instruction  instruction: instructionList){
            System.out.println(instruction.getSubject());
        }
        TimetableDto.MyTimeTable result = TimetableDto.MyTimeTable.builder().myCourse(instructionList).build();
        return result;
    }

    public boolean saveTimeTable(TimetableDto.SaveTimeTable req){
        Student student = userRepository.findById(req.getUserId()).orElseThrow(UserNotFoundException::new);
        ArrayList<Timetable> timetables = new ArrayList<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        for (Instruction instruction: req.getMyCourse()){
            instruction.setChoosed();
            instructions.add(instruction);
            Timetable timetable = new Timetable();
            timetable.setStudent(student);
            timetable.setInstruction(instruction);
            timetables.add(timetable);
        }
        timeTableRepository.deleteAllByUser(student);
        instructionRepository.saveAll(instructions);
        timeTableRepository.saveAll(timetables);
        return true;
    }
}
