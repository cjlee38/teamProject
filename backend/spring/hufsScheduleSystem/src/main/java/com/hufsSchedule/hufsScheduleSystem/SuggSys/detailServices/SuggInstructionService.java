package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Apriori;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.AssociationRule;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.AssociationRuleObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import org.springframework.data.relational.core.sql.In;

import java.util.*;
import java.util.stream.Collectors;

public class SuggInstructionService {
    public static List<WeightInstruction> initValidInstructions(List<Instruction> entireInstructions, List<Instruction> takenInstructions, List<Instruction> selectedInstructions, User userInfo) {
        List <Instruction> removeInstructions = new ArrayList<>();
        removeInstructions.addAll(takenInstructions);
        removeInstructions.addAll(selectedInstructions);

        List<String> names = new ArrayList<>(Arrays.asList(userInfo.getMajor(), userInfo.getSecondMajor(), userInfo.getMinor(), "교양"));
        if (userInfo.getTeaching()) {
            names.add("교육학");
        }


        removeInstructions.addAll(entireInstructions.stream()
                .filter(x -> !names.contains(x.getDept()))
                .collect(Collectors.toList())
        ); // 1전공(e.g. 경영학전공), 이중전공(융복합소프트웨어전공), 부전공, 교육학, 교양에 포함되지 않는 과목돌은 삭제
        List<Instruction> removed =  removeInstructionsByList(entireInstructions, removeInstructions);

        return addWeightToInstructions(removed, new Float(0));

    }

    public static List<WeightInstruction> addWeightToInstructions(List<Instruction> instructions, Float weight) {
        List<WeightInstruction> weighted = new ArrayList<>();
        for (Instruction instruction : instructions) {
            weighted.add(new WeightInstruction(weight, instruction));
        }
        return weighted;
    }

    public static void applyNcssInstructions(Map<String, List<CourseEnums>> remainCourses, List<WeightInstruction> validInstructions) {
        List<CourseEnums> flattenCourses = new ArrayList<>();
        for (String key : remainCourses.keySet()) {
            flattenCourses.addAll(remainCourses.get(key));
        }
        // 에러날거같은데
        Float one = new Float(1.1);
        for ( CourseEnums course : flattenCourses ){
            validInstructions.stream()
                    .filter(x -> x.getInstruction().getInstructionNumber().substring(0,6).equals(course.getCourseNumber()))
                    .forEach(x -> x.setWeight(one));
        }
    }


    public static List<Instruction> removeInstructionsByList(List<Instruction> entireInstructions, List<Instruction> removeInstructions) {
        List<String> removeCourseNumber = new ArrayList<>();
        removeInstructions.stream().forEach(x -> removeCourseNumber.add(x.getInstructionNumber()));
        return entireInstructions.stream()
                .filter(x -> !removeCourseNumber.contains(x.getInstructionNumber().substring(0,6)))
                .collect(Collectors.toList()); // 기존에 선택한 과목들 삭제
    }

    public static void tuneInstructionWeights(List<WeightInstruction> validInstruction, Map<String, List<CourseEnums>> remainCourses, Long userId) {
        // 1. apriori 결과
        // 2. choosed 배수 따라 조정
        // 3. 전필
        applyAssociationRule(validInstruction, userId); // should be within loop
//        applyCrowdedInstructions(validInstruction);
        applyNcssInstructions(remainCourses, validInstruction); // 전필
    }

//    public static void applyCrowdedInstructions(List<WeightInstruction> validInstructions) {
//        validInstructions.stream()
//                .filter(x -> x.getInstruction().getNumberOfPeople() <= x.getInstruction().getChoosed() * 1.5)
//                .forEach(x -> x.setWeight(x.getWeight()/2));
//    }

    public static Set<Set<String>> getTransactions(List<TimetableDto.findInstructionCode> dataset, Long userId) {
        Set<Set<String>> transactions = new HashSet<>();
        List<Long> Ids = new ArrayList<>();
        for (TimetableDto.findInstructionCode data : dataset) {
            Long id = data.getUserId();
            if ( ! id.equals(userId)) { Ids.add(data.getUserId()); }
        } // get unique IDs without userID


        for (Long id : Ids) {
            transactions.add(
                    dataset.stream()
                    .filter(x -> x.getUserId().equals(id))
                    .map(TimetableDto.findInstructionCode::getInstructionNumber)
                    .collect(Collectors.toSet())
            );
        }

        return transactions;
    }

    public static Set<String> getUserTransaction(List<TimetableDto.findInstructionCode> dataset, Long userId) {
        return new HashSet<>(
                dataset.stream()
                .filter(x -> x.getUserId().equals(userId))
                .map(TimetableDto.findInstructionCode::getInstructionNumber)
                .collect(Collectors.toSet())
        );
    }

    public static List<TimetableDto.findInstructionCode> temp() {
        List<TimetableDto.findInstructionCode> list = new ArrayList<>();
        Set<String> a = new HashSet<>(Arrays.asList("컴퓨터프로그래밍1","운영체제","컴퓨터논리개론","소프트웨어공학","정보보안","컴퓨터구조","자료구조","데이터베이스","컴퓨터네트워크","컴퓨터프로그래밍2"));
        Long aa = new Long(1);
        for (String name : a) {
            list.add(new TimetableDto.findInstructionCode(aa, name));
        }
        Long bb = new Long(2);
        Set<String> b = new HashSet<>(Arrays.asList("컴퓨터프로그래밍1", "컴퓨터논리개론", "컴퓨터프로그래밍2", "자료구조","운영체제","알고리즘","객체지향프로그래밍","소프트웨어공학","컴퓨터네트워크","정보보안"));
        for (String name : b) {
            list.add(new TimetableDto.findInstructionCode(bb, name));
        }
        Long cc = new Long(3);
        Set<String> c = new HashSet<>(Arrays.asList("컴퓨터구조","자료구조","데이터베이스"));
        for (String name : c) {
            list.add(new TimetableDto.findInstructionCode(cc, name));
        }
        Long dd = new Long(4);

        Set<String> d = new HashSet<>(Arrays.asList("컴퓨터프로그래밍1","컴퓨터논리개론","공학설계","컴퓨터프로그래밍2","컴퓨터구조","자료구조","운영체제","알고리즘","객체지향프로그래밍","소프트웨어공학","컴퓨터네트워크","창업및기술경영","데이터베이스"));
        for (String name : d) {
            list.add(new TimetableDto.findInstructionCode(dd, name));
        }

        return list;
    }
    public static void applyAssociationRule(List<WeightInstruction> instructions, Long userId) {
        //load dataset
//        List<TimetableDto.findInstructionCode> dataset = courseRepositorySupport.findInstructionCodeByMajor(); // need to be fixed
        List<TimetableDto.findInstructionCode> dataset = temp();

        // Apriori
        Set<Set<String>> transactions = getTransactions(dataset, userId);

        Float minSupport = (float)0.5;
        Apriori apriori = new Apriori(minSupport, transactions);
        apriori.run();

        // Association rule
        String metric = "lift";
        Float minLift = (float)1.0;
        AssociationRule associationRule = new AssociationRule(apriori.getResult(), metric, minLift);
        associationRule.run();

        Set<String> userTransaction = getUserTransaction(dataset, userId);
        for (AssociationRuleObj rule : associationRule.getRules()) {
            if (userTransaction.contains(rule.getAntecedent()) && !userTransaction.contains(rule.getConsequent())) {

            }
        }
        // insturction : 들울 수 있는 강의 : consequent
        for (WeightInstruction instruction : instructions) {
            String instructionNumber = instruction.getInstruction().getInstructionNumber().substring(0,6);
            Integer count = new Integer(0);
            Float sum = new Float(0.0);
            for (AssociationRuleObj rule : associationRule.getRules()) {
                // userTransaction에 antecedent가 포함되고, getConsequent가 포함되지 않아야 count
                if(userTransaction.contains(rule.getAntecedent()) && !userTransaction.contains(rule.getConsequent())) {
                    sum += rule.getConfidence();
                    count++;
                }
            }
            if (count != 0) { instruction.setWeight(sum/(float)count); }

        }
        // antesequent : 내가 들은 것
        // consequent : 들어야 할 것


    }


}
