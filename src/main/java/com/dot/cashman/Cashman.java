package com.dot.cashman;

import java.util.List;
import java.util.logging.Logger;

import com.dot.cashstore.CashStore;
import com.dot.cashstore.CashStoreException;
import com.dot.cashstore.Denomination;

public class Cashman {

    private static Logger logger = Logger.getLogger(Cashman.class.getName());

    private static Cashman cashman;

    private CashStore cashStore;

    private Cashman(CashStore cashStore) {
        this.cashStore = cashStore;
    }

    public static Cashman createWith(CashStore cashStore) {
        if (cashman == null) {
            cashman = new Cashman(cashStore);
            logger.info("Initialised Cashman.");
        }
        return cashman;
    }

    public void dispense(int amount) {
        try {
            DispenseCombinationCalculator calculator = new DispenseCombinationCalculator();
            List<Integer[]> dispenseCombination = calculator.calculateFor(amount, cashStore);

            Integer[] firstCombination = dispenseCombination.get(0);
            updateCashStoreWith(firstCombination);
            logger.info("Dispensed $" + amount + " using " + firstCombination[0] + " note(s) of $"
                    + Denomination.TWENTY.value() + " and " + firstCombination[1] + " note(s) of $"
                    + Denomination.FIFTY.value());
        } catch (DispenseCombinationException | CashStoreException exception) {
            logger.severe(exception.getMessage());
        }
    }

    private void updateCashStoreWith(Integer[] combination) throws CashStoreException {
        cashStore.removeNotes(Denomination.TWENTY, combination[0]);
        cashStore.removeNotes(Denomination.FIFTY, combination[1]);
    }

}
