package com.dot;

import java.util.HashMap;
import java.util.Map;

public class CashStore {

    enum Denomination {
        FIFTY, TWENTY;
    }

    private Map<Denomination, Integer> store = new HashMap();

    public void initialiseStore(int numberOfFifties, int numberOfTwenties) {
        store.put(Denomination.FIFTY, 10);
        store.put(Denomination.TWENTY, 10);
    }

    public void addNote(Denomination denomination, int number) {
        int numberOfNotes = store.get(denomination);
        numberOfNotes += number;
        store.put(denomination, numberOfNotes);
    }

    public void removeNote(Denomination denomination, int number) throws Exception {
        int numberOfNotes = store.get(denomination);
        numberOfNotes -= number;

        if(numberOfNotes < 0) {
            throw new Exception("Cannot remove more notes than there already are in the store.");
        }

        store.put(denomination, numberOfNotes);
    }
}
