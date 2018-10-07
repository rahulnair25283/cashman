package com.dot.cashman;

import java.util.ArrayList;
import java.util.List;

import com.dot.cashstore.CashStore;
import com.dot.cashstore.Denomination;

public class DispenseCombinationCalculator {

    public List<Integer[]> calculateFor(int amount, CashStore cashStore)
            throws DispenseCombinationException {
        DispenseCombinationCalculator calculator = new DispenseCombinationCalculator();

        List<Integer[]> dispenseCombination = calculator.calculateFor(amount, cashStore,
                new int[cashStore.getDenominations().size()], 0);

        if (dispenseCombination.isEmpty()) {
            throw new DispenseCombinationException("Failed to calcualte legal dispense combination for $" + amount);
        }
        
        return dispenseCombination;
    }

    private List<Integer[]> calculateFor(int amount, CashStore cashStore, int[] combination,
            int position) {

        List<Integer[]> dispenseCombination = new ArrayList<>();
        List<Integer> denominations = Denomination.getValues();

        int calculatedAmount = calculate(denominations, combination);
        if (calculatedAmount == amount) {
            dispenseCombination.add(copy(combination));
        } else if (calculatedAmount < amount) {
            for (int i = position; i < denominations.size(); i++) {
                int numberOfNotes = cashStore
                        .getNumberOf(Denomination.fromValue(denominations.get(i)));
                if (numberOfNotes > combination[i]) {
                    int[] newCombination = combination.clone();
                    newCombination[i]++;
                    List<Integer[]> newDispenseCombination = calculateFor(amount, cashStore,
                            newCombination, i);

                    if (newDispenseCombination != null) {
                        dispenseCombination.addAll(newDispenseCombination);
                    }

                }
            }
        }

        return dispenseCombination;

    }

    private int calculate(List<Integer> values, int[] combination) {
        int computedAmount = 0;
        for (int i = 0; i < combination.length; i++) {
            computedAmount += values.get(i) * combination[i];
        }
        return computedAmount;
    }

    private Integer[] copy(int[] combination) {
        Integer[] copy = new Integer[combination.length];
        for (int i = 0; i < combination.length; i++) {
            copy[i] = combination[i];
        }
        return copy;
    }

}
