package main;

import circuit.CircuitResistor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Printer {

    /**
     * method for user input and output to console
     */
    public void printCr(){
        CircuitResistor cr = new CircuitResistor();
        boolean cyklus = true;
        while(cyklus){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.println("Zadejte hodnotu prvního odporu (v Ω)");
                cr.setResistor1(scn.nextInt());
                System.out.println("Zadejte Hodnotu druhého odporu (v Ω)");
                cr.setResistor2(scn.nextInt());
                System.out.println("Zadejte napětí zdroje (ve Voltech)");
                cr.setSource(scn.nextInt());
                System.out.println("Zadejte způsob zapojené (0 = sériové, 1 = paraelní)");
                cr.setSerial(scn.nextInt());
                cyklus = false;
            }catch(InputMismatchException I){
                System.out.println("\033[31m"+"Zadejte číslo!" + "\033[0m");
            }catch(Exception e){
                System.out.println("\033[31m"+ e.getMessage() + "\033[0m\n");
            }
        }
        System.out.println(cr.diagram() + "\n" + "Odpor R =" + cr.resistance() + "Ω\nProud I =" + cr.currnet()+"A");
        try{
            System.out.println("Proud I2 =" + cr.currnetS1() + "A\nProud I3 =" + cr.currnetS2()+"A");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ur1 =" + cr.res1V() + "V\nUr2 =" + cr.res2V()+"V");
    }
}