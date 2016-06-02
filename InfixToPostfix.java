import java.util.*;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
/** The <tt>InfixToPostfix</tt> class takes a mathamatical expression from in-fix notation to post-fix notation.
 *  The final data is held in a Deque that is being used as a stack. During processing two Deques are used,
 *  one of them is the final Deque that is used as a stack the other is a Deque being used as a queue. The 
 *  precendece for the operators is as follows "(),^,* = / = %,+ = -". This class also comes with an
 *  overwritten iterator to go through the final postfix expression.
 *
 *  @author Erwin Seguinte
 *  @version Mar 26, 2015
 */
public class InfixToPostfix {

    private Deque<String> postfix; // Used as a queue of String
    
    /** This constructor in <tt>InfixToPostfix</tt> processes the in-fix using the Shunting-Yard Algorithm.
     *  The process uses the precedence for operators stated in the class discription ("(),^,* = / = %,+ = -").
     *  However, the process does not account for missing left parentheses.
     *
     *  @param The mathamatical expression in string form.
     *  @throws IllegalArgumentException if there is a missing right parentheses.
     *  @throws IllegalArgumentException if a non-digit, non-mathimatical operator and non-variable is used
     */
    public InfixToPostfix(String infix) {
    try{
        Deque<String> postfix = new LinkedList<String>(); // Build postfix as a queue representing a postfix expression
        Deque<String> stack = new LinkedList<String>();   // Build stack as a stack representing the operator stack
        String variable ="";
        String operator = "+-*/%^()";
        String op1 = "*/%";
        String op2 = "+-*/%";
        String op3 = "^*/%";
        String digit = "0987654321.";
        for(int i=0; i < infix.length(); i++){
            String test = infix.substring(i,i+1);
            if (digit.contains(test)){
               int x = i;
               while((x < infix.length()-1)&&(digit.contains(infix.charAt(x+1)+""))){
                  x++;
               }
               postfix.addLast(infix.substring(i,x+1));
               i=x;
            }
            else if (Character.isLowerCase(test.charAt(0))){
               int x = i;
               int counter = i+1;
               while((x+1<infix.length())&&(Character.isLowerCase(infix.charAt(x+1)))){
                  counter++;
                  x++;
               }
               postfix.addLast(infix.substring(i,counter));
               i=counter-1;
            }else if(test.equals(" ")){
            
            }else{ 
               if((stack.peekFirst()==null)&& (operator.equals(test))){
                  stack.addFirst(test);
               }
               else if(test.equals(")")){
                  while(!(stack.getFirst().equals("("))){
                     postfix.addLast(stack.removeFirst());
                     if(stack.peekFirst()==null){
                        throw new IllegalArgumentException();   
                     }  
                  }
                  stack.removeFirst();
               }
               else if(test.equals("(")){
                  stack.addFirst(test);
               }
               else if(test.equals("^")){
                  stack.addFirst(test);    
               }
               else if(op1.contains(test)){
                  while((stack.peekFirst()!=null)&& (op3.contains(stack.peekFirst()))){
                     postfix.addLast(stack.removeFirst());      
                  }
                  stack.addFirst(test);

               }
               else if(test.equals("+")||test.equals("-")){
                  while((stack.peekFirst()!=null)&& (op2.contains(stack.peekFirst()))){
                     postfix.addLast(stack.removeFirst());      
                  }
                  stack.addFirst(test);

               }
               else{
                  throw new IllegalArgumentException();
               }
            }
        }
        while(stack.peekFirst()!=null){
            if(stack.peekFirst().equals("(")){
               throw new IllegalArgumentException();  
            }
            postfix.add(stack.removeFirst());
        }
        this.postfix=postfix;
    }catch(Exception e){
      throw new IllegalArgumentException();  
    }
   }
   
      public Deque<String> convert(){
      return postfix;
   }   
    /**  This Iterator <tt>InfixToPostfix</tt> allows the values in the Deque to be outputted and
     *   also overwrites the orignal Iterator class. This contains the hasNext() and next() methods
     *   that can be used to output the data.
     */
    public Iterator<String> iterator() {
        return new PostfixIterator(postfix) ;
    }
    // Simple embedded unit-test for InfixToPostfix
    public static void main(String[] args) {
        String fin = "";
        InfixToPostfix test = new InfixToPostfix("1.2345678901234567890");
        Iterator<String> itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("1.2345678901234567890")+": Checking 12345678901234567890 = 12345678901234567890");
        fin="";
        test = new InfixToPostfix("33+3");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin);//.equals("235*+")+": Checking 2+3*5 = 235*+");
        fin="";

        test = new InfixToPostfix("2+3*5");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("235*+")+": Checking 2+3*5 = 235*+");
        fin="";
        test = new InfixToPostfix("hello");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("hello")+": Checking hello = hello");
        fin="";
        test = new InfixToPostfix("velocity+3.0");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("velocity3.0+")+": Checking velocity+3.0 = velocity3.0+");
        fin="";
        test = new InfixToPostfix("( 5 + 3 )*12/3");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("53+12*3/")+": Checking (5+3)*12/3 = 53+12*3/");
        fin="";
        test = new InfixToPostfix("5+3*6+7*8");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("536*+78*+")+": Checking 5+3*6+7*8 = 536*+78*+");
        fin="";
        test = new InfixToPostfix("");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("")+": Checking \"\" = \"\"");
        fin="";
        test = new InfixToPostfix("3%3");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("33%")+": Checking 3%3 = 33%");
        fin="";
        test = new InfixToPostfix("(((abc)))^(625342654*2)");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("abc6253426542*^")+": Checking (((abc)))^(625342654*2) = abc6253426542*^");
        fin="";                
        test = new InfixToPostfix("30+6*7/(3+5)^6");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("3067*35+6^/+")+": Checking 30+6*7/(3+5)^6 = 3067*35+6^/+");
        fin="";  
        test = new InfixToPostfix("1+2+3*4^5^(6+7)*8+9.0");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("12+34567+^^*8*+9.0+")+": Checking 1+2+3*4^5^(6+7)*8+9 = 12+34567+^^*8*+9+");
        fin="";      
        test = new InfixToPostfix("12345678901234567890+12345678901234567890*5");
        itr = test.iterator();
        while(itr.hasNext()){
            fin=fin+itr.next();
        }
        System.out.println(fin.equals("12345678901234567890123456789012345678905*+")+": Checking 12345678901234567890+12345678901234567890*5 \n\t\t= 12345678901234567890123456789012345678905*+");
        fin="";
        test = new InfixToPostfix("12345678901234567890+12345678901234567890*5");
        itr = test.iterator();
        fin=itr.next();
        System.out.println(fin.equals("12345678901234567890")+": Iterator Test");
        fin=itr.next();
        System.out.println(fin.equals("12345678901234567890")+": Iterator Test");
        fin=itr.next();
        System.out.println(fin.equals("5")+": Iterator Test");
        fin=itr.next();
        System.out.println(fin.equals("*")+": Iterator Test");
        fin=itr.next();
        System.out.println(fin.equals("+")+": Iterator Test");
        fin=""; 
        try {
            test = new InfixToPostfix("A  +#*$");  
        }
        catch (IllegalArgumentException e) {
            System.out.println("true: Invalid character put into express");
        }
        try {
            test = new InfixToPostfix("7+8*)8+3");  
        }
        catch (IllegalArgumentException e) {
            System.out.println("true: Missing left parentheses ");
        }
        try {
            test = new InfixToPostfix("(7+8*8+3");  
        }
        catch (IllegalArgumentException e) {
            System.out.println("true: Missing right parentheses");
        }
    }
}

// PostfixIterator implements Iterator<String>, so is an acceptable type
// whenever Iterator<String> is specified as the type (eg, iterator() above).
class PostfixIterator implements Iterator<String> {

    private Deque<String> itr;
    private Deque<String> copy;
    public PostfixIterator(Deque<String> postfix) {
        this.itr = postfix;
    }

    public boolean hasNext() { 
        return ((itr.peekFirst())!=null);
    }

    public String next() {
        return itr.removeFirst();
    }
    public void remove() {
        
    }

}