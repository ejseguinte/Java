/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roller;

import Dice.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EJSeguinte
 */
public class Roller {
    private Die dice;
    private List<Integer> list;
    private static Roller single;
    
    private Roller(){
        list = new ArrayList<>();
        dice = new sixSided();
    }

    public static Roller getRoller(){
        if (single == null){
            return new Roller();
        }else{
            return single;
        }
    }
    
    public void setDie(int dieValue){
        switch (dieValue){
            case 3:
                dice = new threeSided();
                break;
            case 6:
                dice = new sixSided();
                break;
            case 8:
                dice = new eightSided();
                break;
            case 10:
                dice = new tenSided();
                break;
            case 12:
                dice = new twelveSided();
                break;
            case 20:
                dice = new twentySided();
                break;
            default:
                dice = new sixSided();
                break;
        }
    }
    
    public List roll(int number){
        list.clear();
        for (int i = 0; i < number; i++){
            list.add(dice.roll());
        }
        System.out.println();
        printRoll();
        return list;
    }
    
    public List targetRoll(int target,int number){
        list.clear();
        int success = 0;
        for (int i = 0; i < number; i++){
            int roll = dice.roll();
            list.add(roll);
            if(roll >= target)
                ++success;
        }
        System.out.println();
        printRoll();
        printRoll(target,success);
        return list;
    }
    
    public void printRoll(){
        System.out.println(dice.getSides()+"-Sided Die");
        for(int i = 0; i < list.size(); i++){
            if(i == list.size()-1)
                System.out.print(list.get(i));
            else 
                System.out.print(list.get(i)+", ");
        }
        System.out.println();
    }
    
    public void printRoll(int target,int success){
        System.out.println("Target: "+ target);
        System.out.println("Successes: "+ success);
    }
    
}
