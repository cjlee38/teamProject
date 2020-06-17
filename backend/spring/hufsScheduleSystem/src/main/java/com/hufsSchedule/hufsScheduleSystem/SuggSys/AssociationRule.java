package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Sets;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.AssociationRuleObj;

import java.util.*;

public class AssociationRule {
    private Map<Set<String>, Float> itemset;
    private String metric;
    private Float min_threshold;
    private List<AssociationRuleObj> result;

    public AssociationRule(Map<Set<String>, Float> itemset, String metric, Float min_threshold) {
        this.itemset = itemset;
        this.metric = metric;
        this.min_threshold = min_threshold;
        this.result = run();
    }

    public List<AssociationRuleObj> getResult() {
        return result;
    }

    private Float scoreByMetric(String metric, Float sAC, Float sA, Float sC) {
        Float score;
        if (metric.equals("support")) { score = getSupport(sAC); }
        else if (metric.equals("confidence")) { score = getConfidence(sAC, sC); }
        else if(metric.equals("lift")) { score = getLift(sAC, sC); }
        else { score = null; }

        return score;
    }
    private Float getSupport(Float sAC) { return sAC; }
    private Float getConfidence(Float sAC, Float sC) { return sAC / sC; }
    private Float getLift(Float sAC, Float sC) {
        return (sAC/sC) / sC;
    }

    public List<AssociationRuleObj> run() {
        List<Set<String>> rule_antecedents = new LinkedList<>();
        List<Set<String>> rule_consequents = new LinkedList<>();
        List<List<Float>> rule_supports = new LinkedList<>();
        List<Float> supports = new LinkedList<>();
        List<Float> confidences = new LinkedList<>();
        List<Float> lifts = new LinkedList<>();

        for (Set<String> item : itemset.keySet()) {
            Float sAC = itemset.get(item);
            for (Integer idx = item.size()-1; idx > 0; idx--) {
                Set<Set<String>> combination = Sets.combinations(item, idx);
                for (Set<String> comb : combination) {
                    Set<String> antecedent  = comb;
                    Set<String> consequent = Sets.difference(item, comb);

                    Float sA = itemset.get(antecedent);
                    Float sC = itemset.get(consequent);
                    Float score = scoreByMetric(metric, sAC, sA, sC);
                    if (score >= min_threshold) {
                        rule_antecedents.add(antecedent);
                        rule_consequents.add(consequent);
                        rule_supports.add(Arrays.asList(sAC, sA, sC));
                    }
                }
            }
        }

        for (List<Float> row : rule_supports) {
            supports.add(scoreByMetric("support", row.get(0), row.get(1), row.get(2)));
            confidences.add(scoreByMetric("confidence", row.get(0), row.get(1), row.get(2)));
            lifts.add(scoreByMetric("lift", row.get(0), row.get(1), row.get(2)));
        }

        List<AssociationRuleObj> rules = new ArrayList<>();
        for (Integer idx = 0; idx < rule_antecedents.size(); idx++) {
            rules.add(new AssociationRuleObj(
                    rule_antecedents.get(idx),
                    rule_consequents.get(idx),
                    rule_supports.get(idx),
                    supports.get(idx),
                    confidences.get(idx),
                    lifts.get(idx)));
        }

        return rules;
    }


}
