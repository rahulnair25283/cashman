package com.dot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class CashStore {

    enum Denomination {
        TWENTY(20), FIFTY(50), HUNDRED(100);

        int value;

        Denomination(int value) {
            this.value = value;
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

    private static CashStore cashStore;

    private Map<Denomination, Integer> store = new HashMap<>();

    private CashStore(int numberOfTwenties, int numberOfFifties) {
        store.put(Denomination.TWENTY, numberOfTwenties);
        store.put(Denomination.FIFTY, numberOfFifties);
    }

    public static CashStore openWith(int numberOfTwenties, int numberOfFifties) {
        if (cashStore == null) {
            cashStore = new CashStore(numberOfTwenties, numberOfFifties);
        }
        return cashStore;
    }

    public void add(Denomination denomination, int number) throws Exception {
        int numberOfNotes = getNumberOf(denomination);
        store.put(denomination, numberOfNotes + number);
    }

    public void remove(Denomination denomination, int number) throws Exception {
        int numberOfNotes = store.get(denomination);
        numberOfNotes -= number;
        if (numberOfNotes < 0) {
            throw new Exception("Cannot remove more notes than there already are in the store.");
        }

        store.put(denomination, numberOfNotes);
    }

    public int getNumberOf(Denomination denomination) {
        if (!store.containsKey(denomination)) {
            return -1;
        }
        return store.get(denomination);
    }

    public void destroy() {
        cashStore = null;
    }
}
