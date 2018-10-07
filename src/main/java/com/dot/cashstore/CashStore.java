package com.dot.cashstore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class CashStore {

    private static Logger logger = Logger.getLogger(CashStore.class.getName());

    private static CashStore cashStore;

    private Map<Denomination, Integer> store = new HashMap<>();

    private CashStore(int numberOfTwenties, int numberOfFifties) {
        store.put(Denomination.TWENTY, numberOfTwenties);
        store.put(Denomination.FIFTY, numberOfFifties);
    }

    public static CashStore openWith(int numberOfTwenties, int numberOfFifties) {
        if (cashStore == null) {
            cashStore = new CashStore(numberOfTwenties, numberOfFifties);
            logger.info("Initialised cash store with " + numberOfTwenties + " notes of $"
                    + Denomination.TWENTY.value() + " and " + numberOfFifties + " notes of $"
                    + Denomination.FIFTY.value());
        }
        return cashStore;
    }

    public Set<Denomination> getDenominations() {
        return store.keySet();
    }

    public void addNotes(Denomination denomination, int number) {
        int numberOfNotes = getNumberOf(denomination);
        store.put(denomination, numberOfNotes + number);
        logger.info("Added " + number + " notes of $" + denomination.value());
    }

    public void removeNotes(Denomination denomination, int number) throws CashStoreException {
        int numberOfNotes = store.get(denomination);
        numberOfNotes -= number;
        if (numberOfNotes < 0) {
            throw new CashStoreException(
                    "Failed to remove notes from the store; there aren't enough notes to remove.");
        }

        store.put(denomination, numberOfNotes);
        logger.info("Removed " + number + " notes of $" + denomination.value());
    }

    public int getNumberOf(Denomination denomination) {
        return store.get(denomination);
    }

    public void destroy() throws CashStoreException {
        if (cashStore == null) {
            throw new CashStoreException("Cash store cannot be close. It is not open.");
        }
        cashStore = null;            
    }
}
