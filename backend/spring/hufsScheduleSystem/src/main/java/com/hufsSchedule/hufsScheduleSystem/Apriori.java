package com.hufsSchedule.hufsScheduleSystem;

import com.google.common.collect.Sets;

import java.util.*;

public class Apriori {
    private Float minSupport;
    private Set<Set<String>> dataset;
    private Set<Set<String>> result;

    public Apriori(Float minSupport, Set<Set<String>> dataset) {
        this.minSupport = minSupport;
        this.dataset = dataset;
        this.result = new HashSet<>();
    }

    public Set<Set<String>> getResult() {
        return result;
    }

    public void run() {

        Set<String> itemset = createSet(dataset);
        Integer level = 1;
        Integer maxLevel = itemset.size();

        while (level <= maxLevel) {
            Set<Set<String>> combinations = getCombinations(itemset, level);
            Set<Set<String>> supportedItemset = getSupportedItemset(combinations);
            result.addAll(supportedItemset);
            itemset = createSet(supportedItemset);
            level++;

            if (itemset.size() <= 1) {
                break;
            }
        }

    }

    public Set<Set<String>> getCombinations(Set<String> set, Integer level) {
        return Sets.combinations(set, level);
    }

    // 중복없는 데이터를 구하는 함수
    public Set<String> createSet(Set<Set<String>> data) {
        Set<String> set = new HashSet<>();
        for (Set<String> transaction : data) {
            for ( String item : transaction) {
                set.add(item);
            }
        }

        return set;
    }

    public Float calcSupport(Set<String> itemset) {
        Integer occurence = 0;
        for( Set<String> transaction : dataset) {
            if (transaction.containsAll(itemset)) {
                occurence++;
            }
        }
        return occurence / ((float)dataset.size());
    }

    public Set<Set<String>> getSupportedItemset(Set<Set<String>> itemset) {
        Map<Set<String>, Float> map = new HashMap<>();
        for (Set<String> comb : itemset) {
            map.put(comb, calcSupport(comb));
        } // support 계산

        map.entrySet().removeIf(x -> x.getValue() < minSupport); // minsupport보다 낮으면 제거



        return map.keySet();
    }
}
