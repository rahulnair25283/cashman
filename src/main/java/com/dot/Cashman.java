package com.dot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dot.CashStore.Denomination;

public class Cashman {

    public void something(CashStore cashStore, int amount) throws Exception {

        Map<Denomination, Integer> result = new HashMap<>();

        List<Integer> denominations = Denomination.getValues();

        System.out.println(denominations);

        List<Integer> closestDenominations = denominations.stream()
                .filter(denomination -> denomination <= amount
                        && cashStore.getNumberOf(Denomination.fromValue(denomination)) > 0)
                .collect(Collectors.toList());

        System.out.println(closestDenominations);
        Collections.sort(closestDenominations, Collections.reverseOrder());
        System.out.println(closestDenominations);

        int balance = amount;
        
        for (int i = 0; i < closestDenominations.size(); i++) {
            for (int j = i; j < closestDenominations.size(); j++) {
                int count = balance/closestDenominations.get(j);
                result.put(Denomination.fromValue(closestDenominations.get(j)), count);
                balance = balance%closestDenominations.get(j);
                
                if (balance == 0) {
                    break;
                } else if (balance > 0) {
                    continue;
                }
            }
            
            if (balance == 0) {
                break;
            } else if(balance > 0) {
                balance = amount;
                result.clear();
                continue;
            }
        }

//        for (int denomination : closestDenominations) {
//            int numberOfNotes = cashStore.getNumberOf(Denomination.fromValue(denomination));
//            System.out.println("number of notes of " + denomination + " is " + numberOfNotes);
//
//            for (int i = 1; i <= numberOfNotes; i++) {
//
//                sum += denomination;
//
//                if (sum == amount) {
//                    result.put(Denomination.fromValue(denomination), i);
//                    break;
//                }
//                
//                if (sum > amount) {
//                    sum -= denomination;
//                    result.put(Denomination.fromValue(denomination), i-1);
//                    break;
//                }
//            }
//        }
        
        if (result.isEmpty()) {
            System.out.println("Not possibru!");
        }

        System.out.println(result);

    }

}
