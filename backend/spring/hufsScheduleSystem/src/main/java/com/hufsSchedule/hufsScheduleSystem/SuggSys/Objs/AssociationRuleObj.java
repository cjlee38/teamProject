package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import java.util.Set;

public class AssociationRuleObj {
    public Set<String> antecedent;
    public Set<String> consequent;
    public Float support;
    public Float confidence;
    public Float lift;

    public AssociationRuleObj(Set<String> antecedent, Set<String> consequent,
                              Float support, Float confidence, Float lift) {
        this.antecedent = antecedent;
        this.consequent = consequent;
        this.support = support;
        this.confidence = confidence;
        this.lift = lift;
    }
}
