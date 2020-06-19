package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import lombok.Getter;
import java.util.Set;

@Getter
public class AssociationRuleObj {
    private Set<String> antecedent;
    private Set<String> consequent;
    private Float support;
    private Float confidence;
    private Float lift;

    public AssociationRuleObj(Set<String> antecedent, Set<String> consequent,
                              Float support, Float confidence, Float lift) {
        this.antecedent = antecedent;
        this.consequent = consequent;
        this.support = support;
        this.confidence = confidence;
        this.lift = lift;
    }
}
