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
public class eightSided implements Die{
    private static  int sides;
    private static Random r;
    
    public eightSided(){
        sides = 8;
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
