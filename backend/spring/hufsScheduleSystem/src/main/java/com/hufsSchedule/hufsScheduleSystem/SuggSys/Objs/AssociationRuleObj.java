package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import java.util.List;
import java.util.Set;

public class AssociationRuleObj {
    public Set<String> rule_antecedent;
    public Set<String> rule_consequent;
    public List<Float> rule_support;
    public Float support;
    public Float confidence;
    public Float lift;

    public AssociationRuleObj(Set<String> rule_antecedent, Set<String> rule_consequent, List<Float> rule_support, Float support, Float confidence, Float lift) {
        this.rule_antecedent = rule_antecedent;
        this.rule_consequent = rule_consequent;
        this.rule_support = rule_support;
        this.support = support;
        this.confidence = confidence;
        this.lift = lift;
    }
}
