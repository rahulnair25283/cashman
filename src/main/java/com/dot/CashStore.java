package com.dot;

import java.util.HashMap;
import java.util.Map;

public class CashStore {

    enum Denomination {
        TWENTY, FIFTY, HUNDRED;
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

    public int getNumberOf(Denomination denomination) throws Exception {
        if (!store.containsKey(denomination)) {
            throw new Exception(
                    "The cash store does not contain notes of denominaton " + denomination.name());
        }
        return store.get(denomination);
    }
    
    public void destroy() {
        cashStore = null;
    }
}
