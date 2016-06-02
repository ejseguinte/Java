import java.util.*;

public class Main {
   /** This is a Read, Evaluate, Print, Loop for the <tt>Evaluator</tt> Class. The Program can take
    *  normal mathamatical expressions and evaluate them or create variables with the pattern (name=expression).
    *  To exit the program enter the word "EXIT" as an expression and it will end the program. If an exception is 
    *  found in the program it will indiscriminately output error.
    * 
    *  @param args The string arguments passed in main
    */
    public static void main(String[] args) {
       Evaluator eval = new Evaluator(); 
       Scanner in = new Scanner(System.in);
       System.out.println("Welcome to BigNum Calculator");
       String input="";
       System.out.println();
       System.out.println("Enter \"EXIT\" to quit. \nPlease enter an expression to evaluate:");
       System.out.println();
       while(!input.contains("EXIT")){
         input=in.nextLine();
         if(input.contains("EXIT"))
            return;
         else
            System.out.println(eval.math(input));
       }  
    }
}