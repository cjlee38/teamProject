package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreditRatio {
    public Map<String, String> fieldToMajor;
    public Map<String, Float> ratio;

    public CreditRatio(Map<String, String> fieldToMajor, Map<String, Float> ratio) {
        this.fieldToMajor = fieldToMajor;
        this.ratio = ratio;
    }
}
