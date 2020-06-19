package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Sets;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.AssociationRuleObj;

import java.util.*;


/*
* 참고문헌
* 김용, Apriori 알고리즘 기반의 개인화 정보 추천 시스템 설계 및 구현에 관한 연구
* 한국비블리아학회지 VOL.23. NO.4 (2012):283-308,
* 평가 中 : 본 연구에서는 효과적인 규칙추출 및 정보추천을 위하여 다음과같이 변형된 apriori 알고리즘을 적용하였다.
* 예를 들어, 항목 A와 B가 있다고 가정하고 A와 B에 대한 연관규칙에 대해, 왼쪽 항목집합 A를 선행부라 하고,
* B를 결과부라 가정하고 추천처리를 단순화하기 위하여 다음과 같은 제한을 둔다.
* 1. 선행부와 결과부에는 각각 하나의 항목집함만을 가지는 규칙이 생성된다.
* 예를들어, A -> Z, B -> Y와 같은 규칙이 추출되며,
* A -> Z,Y 혹은 B,A -> Z 와 같은 규칙은 허용되지 않는다
* 2. 규칙을 추춣하는데 있어서 향상도가 1이상인 것만 추출한다. 이를 통하여 통계적으로 의미가 있는 규칙만을 추출할 수 있다.
* 3. 추출된 연관규칙 중에서 하나의 동일한 선행부에 대응되는 결과부의 경우의 수를 제한한다.
* 여기서 결과부에 나타날 수 있는 항목에 대한 경우의 수를 제한하는데,
* 예를 들어 제한수가 3인 경우에 있어서 동일한 선행부에 대하여 4개 이상의 규칙이 추출 될 수 없다.
* 이렇게 결과부에 나타나는 규칙의 수를 제한하는 이유는 연관규칙이 상위지지도를 가지는
* 콘텐츠들 사이에서만 집중되는 경향이 있으므로 특정 콘텐츠에 규칙이 집중되는 문제를 방지함으로써
* 규칙 추출에 따른 유효성을 확보하기 위한 것이다.
* */
public class AssociationRule {
    private Map<Set<String>, Float> itemset;
//    private Set<String> userInstruction;
    private String metric;
    private Float min_threshold;
    private List<AssociationRuleObj> rules;


    public AssociationRule(Map<Set<String>, Float> itemset, String metric, Float min_threshold) {
        this.itemset = itemset;
//        this.userInstruction = userInstruction;
        this.metric = metric;
        this.min_threshold = min_threshold;
        this.rules = new LinkedList<>();
    }

    public List<AssociationRuleObj> getRules() {
        return rules;
    }

    private Float scoreByMetric(String metric, Float sAC, Float sA, Float sC) {
        Float score;
        if (metric.equals("support")) { score = getSupport(sAC); }
        else if (metric.equals("confidence")) { score = getConfidence(sAC, sA); }
        else if(metric.equals("lift")) { score = getLift(sAC, sA, sC); }
        else { score = null; }

        return score;
    }
    private Float getSupport(Float sAC) { return sAC; }
    private Float getConfidence(Float sAC, Float sA) { return sAC / sA; }
    private Float getLift(Float sAC, Float sA, Float sC) {
        return (sAC/sA) / sC;
    }
    private void filterItemset() {
        itemset.entrySet().removeIf(x -> x.getKey().size() > 2);
    }

    public void run() {
        filterItemset();
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
                        rules.add(new AssociationRuleObj(antecedent, consequent, sAC, getConfidence(sAC, sA), getLift(sAC, sA, sC)));
                    }
                }
            }
        }
    }


}
