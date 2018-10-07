package com.dot.cashman;

import java.util.Scanner;

import com.dot.cashstore.CashStore;

public class Main {
    
    private static Scanner io;
    private static Cashman cashman;

    public static void main(String[] args) {
        try {
            io = new Scanner(System.in);
            
            System.out.println("-----------------------------------------");
            System.out.println("Hi, I am Cashman. I love dispensing cash!");
            System.out.println("-----------------------------------------");
            System.out.println("Press 1 to see me in action");
            System.out.println("Press 2 to quit");
            System.out.println("Press any other key to potentially watch me spew uncaught exceptions!");
            
            int option = io.nextInt();
            
            switch(option) {
            case 1:
                start(io);
            case 2:
            default:
                quit(io);
            }
        } finally {
            if (io != null) {
                io.close();                
            }
        }
    }

    private static void start(Scanner io) {
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("I can consume $20 and $50 notes.");
        
        System.out.println("");
        System.out.print("How many $20 notes will I consume? (Remember, if you dont enter a reasonable number, dont blame me for the consequences): ");
        int numberOfTwenties = io.nextInt();
        
        System.out.print("How many $50 notes will I consume? (Reasonable number or death): ");
        int numberOfFifties = io.nextInt();
        
        System.out.println("");
        
        cashman = Cashman.createWith(CashStore.openWith(numberOfTwenties, numberOfFifties));
        
        System.out.println("");
        System.out.println("-----------------------------------------------------");
        System.out.println("I am now fully setup and ready to dispense some cash.");
        System.out.println("");
        
        System.out.println("Press 1 to see me dispense cash like a boss");
        System.out.println("Press 2 to quit");
        System.out.println("Press any other key to potentially watch me spew uncaught exceptions!");
        int option = io.nextInt();
        
        switch(option) {
        case 1:
            System.out.println("------------------------------------------------");
            System.out.print("How much money would you like me to dispense?: $");
            int amount = io.nextInt();
            
            System.out.println("");
            cashman.dispense(amount);
            System.out.println("");
            
        case 2:
        default:
            quit(io);
        }
        
    }
    
    private static void quit(Scanner io) {
        System.out.println("");
        System.out.println("Lucky you! No uncaught exceptions, bye! :(");
        System.out.println("P.S. My unit tests cover a lot more scenarios. ;) ");
        System.out.println("");
        if (io != null) {
            io.close();            
        }
    }
}
