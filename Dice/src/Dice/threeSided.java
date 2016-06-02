/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dice;

import java.util.Random;

/**
 *
 * @author EJSeguinte
 */
public class threeSided implements Die{
    private static  int sides;
    private static Random r;
    
    public threeSided(){
        sides = 3;
        r = new Random();
    }
    
    public int getSides(){
        return sides;
    }
    
    @Override
   public int roll(){
       return r.nextInt(sides-1)+1;
    }
}
