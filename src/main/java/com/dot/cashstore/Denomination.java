package com.dot.cashstore;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public enum Denomination {

    TWENTY(20), FIFTY(50);

    private int value;

    Denomination(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }

    public static List<Integer> getValues() {
        return Lists.newArrayList(Denomination.values()).stream()
                .map(denomination -> denomination.value).collect(Collectors.toList());
    }

    public static Denomination fromValue(int value) {
        Denomination[] denominations = Denomination.values();
        for (Denomination denomination : denominations) {
            if (denomination.value == value) {
                return denomination;
            }
        }
        return null;
    }

}
