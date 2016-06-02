import java.util.*;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.math.BigInteger;
/** The <tt>Evaluator</tt> class takes a mathamatical string expression from in-fix notation to post-fix notation.
 *  Then returns the equivlent value after performing all the math in the string. The <tt>Evaluator</tt> also takes 
 *  variables that are lowercased. To set a variable it requires a name followed by and = and the numerical String expression
 *  To convert to postfix the program uses the <tt>InfixToPostfix</tt> written by Erwin Seguinte
 *
 *  @author Erwin Seguinte
 *  @version May 9, 2015
 */
public class Evaluator {
    private Map<String,String> variable;
    /** This constructor in <tt>Evaluator</tt> initalizes the object to properly handle variables. To do this
     *  the object initalizes a private map into a TreeMap that is conditioned for String to String use. This is 
     *  were all variables are held for the object itself.
     *
     */

    public Evaluator(){
      
      variable = new TreeMap<String,String>();
    }
    /** This method in <tt>Evaluator</tt> processes the variables that need to be set with the pattern (name = expression).
     *  If the expression is a variable the methos removes spaces and seperates the inital string by using the = as the divider.
     *  Then checks to see if the variable name is all lowercase letters then finally sets the value of variable.
     *  If the expression is not a variable is sent to another math methods that excepts InfixToPostfix as a parameter.
     *
     *  @param  math String whether for a variable or a normal expression
     *  @return  String that was evaluated
     *  @throws Error when any kind of exception is found
     *  
     */
    public String math(String math){
    String error = "Error";
    try{
      if((Character.isAlphabetic(math.charAt(0)))&&(math.contains("="))){
            String name = math.substring(0,math.indexOf("="));
            name=name.trim();
            for(int i = 0; i < name.length(); i++){
               if(!(Character.isLowerCase(name.charAt(i))))
                  throw new IllegalArgumentException();
            }  
            String value = math.substring(math.indexOf("=")+1,math.length());
            math=value;
            variable.put(name, math(value));
            return variable.get(name);          
      }
      if (variable.containsKey(math)){
         return variable.get(math);  
      }else{
         InfixToPostfix test = new InfixToPostfix(math);
         return math(test);
      }   
    }catch(Exception e){
      return error;
    }  
   }
    /** This method in <tt>Evaluator</tt> processes the other expressions besides variables.
     *  This is an overloaded method that takes InfixToPostfix instead of Strings to evaluate the expression.
     *  The InfixToPostfix class should catch most errors in the expression if not this method if it throws an error is caught
     *  in the previous method. 
     *
     *  @param postfix InfixToPostfix expression
     *  @return  String that was evaluated
     *  @throws Error when any kind of exception is found
     *  
     */
   public String math(InfixToPostfix postfix){
      Iterator<String> itr = postfix.iterator();
      Deque<String> solution = new LinkedList<String>();
      while(itr.hasNext()){
         String value = itr.next();
         Character item=value.charAt(0);
         if ((Character.isDigit(item))||(item=='.')||(item=='_')){
            solution.addFirst(value);
         }else if(Character.isAlphabetic(item)&&(!variable.containsKey(value))){
            throw new IllegalArgumentException();        
         }else if(Character.isAlphabetic(item)){ 
            solution.addFirst(value); 
         }else{
              String operator = value;
              String temp1 = solution.removeFirst();
              String temp2 = solution.removeFirst();
              if((variable.containsKey(temp2)) && (variable.containsKey(temp1))){
                  temp1=variable.get(temp1);
                  temp2=variable.get(temp2);
              }
              else if (variable.containsKey(temp1))
                  temp1=variable.get(temp1);
              else if (variable.containsKey(temp2))
                  temp2=variable.get(temp2);
              solution.addFirst(operation(temp1,temp2,operator)); 
          }       
      }
      if (solution.size()==1)
         return solution.removeFirst(); 
      else 
         throw new IllegalArgumentException();   
   }   

    /** This is used as an operand comparison for <tt>Evaluator</tt> objects' takes 3 values
      * converts the first two values into Big Integer and does the following operand. Since the Big Integer's
      * power method takes a Big Integer and an int one of the values is converted to an int when math is done
      *
      * @param num1 String that holds digits to be evaluated
      * @param num2 String that holds digits to be evaluated
      * @param operator String that stores the type of operatio to be evaluated
      * @return  returns a string of the operator math of the two digit strings
      */   
   public String operation (String num1, String num2, String operator){
      BigInteger result = new BigInteger("0");
      BigInteger one = new BigInteger(num1);
      BigInteger two = new BigInteger(num2);
      if (operator.equals("+")){
         result=one.add(two);
      }else if (operator.equals("-")){
         result=two.subtract(one);
      }else if (operator.equals("*")){
         result=one.multiply(two);
      }else if (operator.equals("/")){
         result=two.divide(one);
      }else if (operator.equals("%")){
         result=two.mod(one);
      }else if (operator.equals("^")){
         int temp = Integer.parseInt(num1);
         result=two.pow(temp);
      }
      String finalResult = result.toString();
      return finalResult;
   }
   
    // Simple embedded unit-test for Evaluator
    public static void main(String[] args) {
       Evaluator eval = new Evaluator(); 
       System.out.println("Unit Test for Evaluator Class");
       System.out.println(eval.math("2 + 2 + 4").equals("8") +" : Testing 2 + 2 + 4  = 8"); 
       System.out.println(eval.math("2 - 2 - 4").equals("-4")+" : Testing 2 - 2 - 4  = -4");
       System.out.println(eval.math("2 * 2 * 4").equals("16")+" : Testing 2 * 2 * 4  = 16"); 
       System.out.println(eval.math("16 / 2 / 4").equals("2")+" : Testing 16 / 2 / 4 = 2");  
       System.out.println(eval.math("17 % 2 % 1").equals("0")+" : Testing 17 % 2 % 1 = 0");
       System.out.println(eval.math("1 1 1 + 2").equals("Error")+" : Testing 1 1 1 + 2  = Error");
       System.out.println(eval.math("2 ^ 5 ^ 2").equals("33554432")+" : Testing 2 ^ 5 ^ 2  = 33554432");
       System.out.println(eval.math("12345678901234567890 + 12345678901234567890 * 5").equals("74074073407407407340")+
       " : Testing 12345678901234567890 \n+ 12345678901234567890 * 5  \n= 74074073407407407340"); 
       System.out.println(eval.math("velocity = 5").equals("5")+" : Testing 'velocity  = 5'  = 5");
       System.out.println(eval.math("velocity").equals("5")+" : Testing  velocity  = 5"); 
       System.out.println(eval.math("velocity  * 5").equals("25")+" : Testing  velocity  * 5    = 25");
       System.out.println(eval.math("Velocity").equals("Error")+" : Testing  Velocity  = Error");    
    }
}