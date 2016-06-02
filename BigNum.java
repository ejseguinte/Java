/** The <tt>BigNum</tt> class represents any length non-negative integer, and
 *  supports basic operations on them. <tt>BigNum</tt> objects are immutable.
 *
 *  @author Erwin Seguinte
 *  @version Feb 8, 2015
 */
public class BigNum {
    // F i e l d s

    private String num;  // Representation of our number

    // C o n s t r u c t o r s

    /** Constructs a <tt>BigNum</tt> from a non-empty String of digits.
     *  @param num The string to be interpreted.
     *  @throws IllegalArgumentException if num is length zero or
     *      has any non-numeric digits
     *  @throws NullPointerException if num is null
     */
    public BigNum(String num) {
        for (int i=0; i<num.length(); i++) {
            if ( ! Character.isDigit(num.charAt(i)) ) {
                throw new IllegalArgumentException();
            }
        }
        if ( num.length() == 0 ) {
            throw new IllegalArgumentException();
        }
        this.num = num;
    }

    /** Constructs a <tt>BigNum</tt> from a non-negative integer.
     *  @param num The non-negative integer to be interpreted.
     *  @throws IllegalArgumentException if num is negative
     */
    public BigNum(int num) {
        // If num<0, redirected constructor will throw exception due to "-"
        this(""+num);
    }

    /** Constructs a <tt>BigNum</tt> with value zero.
     */
    public BigNum() {
        num="0";
    }

    // P u b l i c   M e t h o d s

    /** Adds two <tt>BigNum</tt> objects' values together and returns a new
      * <tt>BigNum</tt> object with the resulting value.
      *
      * @param other this and other objects get added together
      * @return a new BigNum with the resulting value
      */
    public BigNum add(BigNum other) {
        // Make shorter refer to the shorter num, longer to the longer num
        String shorter = other.num;
        String longer = this.num;
        if (this.num.length() < other.num.length()) {
            shorter = this.num;
            longer = other.num;
        }
        // Prepend zeros to make shorter as long as longer
        while (shorter.length() < longer.length()) {
            shorter = "0" + shorter;
        }
        // Add columns like we did in grade school
        int carry = 0;
        String result = "";
        for (int i=shorter.length()-1; i>=0; i--) {
            int temp = Character.getNumericValue(shorter.charAt(i)) +
                       Character.getNumericValue(longer.charAt(i)) + carry;
            result = (temp%10)+result;
            carry = temp/10;
        }
        // Handle carry-out, if there is one. Return result
        if (carry == 1) {
            result = "1"+result;
        }
        return new BigNum(result);
    }

    /** Returns a string representation of the number. No leading zeros
      * will exist unless the overall value is zero.
      *
      * @return String representation of the BigNum
      */
    public String toString() {
        return num;
    }

    /** Multiplies two <tt>BigNum</tt> objects' values together and returns a new
      * <tt>BigNum</tt> object with the resulting value.
      *
      * @param other this and other objects get multiplied together
      * @return  a new BigNum with the resulting value
      */
    public BigNum multiply(BigNum other){
         // Make shorter refer to the shorter num, longer to the longer num
        String shorter = other.num;
        String longer = this.num;
        int carry = 0;
        int temp2 = 0;
        int place = 0;
        int y = 0;
        BigNum result = new BigNum(0);
        String temp3 = "";
        if (this.num.length() < other.num.length()) {
            shorter = this.num;
            longer = other.num;
        }
        for (int i=shorter.length()-1; i>=0; i--){
            for(int x=longer.length()-1; x>=0; x--){
               temp2 = Character.getNumericValue(shorter.charAt(i)) *
                       Character.getNumericValue(longer.charAt(x)) + carry;
               place = (temp2%10);
               carry = temp2/10;
               if (x-1>=0)
                  temp3=place+temp3;
               else   
                  temp3=temp2+temp3;
            }
            //Add the zero for each new place
            for(int z=0;z<y;z++){
               temp3=temp3+"0";
            }
            BigNum temp = new BigNum(temp3);
            result=result.add(temp);
            y++;
            temp3="";
            carry=0;
        }
        return result;
    }
    /** This returns a true boolean if other 
      * this os less than other
      * 
      * @param other this and other objects compared
      * @return boolean if other this os less than other
      */
    public boolean less(BigNum other){
        String shorter = other.num;
        String longer = this.num;
        if (longer.length()>shorter.length())
            return false;
        else if(longer.length()<shorter.length())
            return true;     
        else if((longer.length()==shorter.length())&&((Character.getNumericValue(longer.charAt(0)))>
            (Character.getNumericValue(shorter.charAt(0)))))
            return false;
        else if((longer.length()==shorter.length())&&((Character.getNumericValue(longer.charAt(0)))<
            (Character.getNumericValue(shorter.charAt(0)))))
            return true;
        else if(longer.equals(shorter))
            return false;        
        else {
            BigNum longest = new BigNum(longer.substring(1,longer.length()));
            BigNum shortest = new BigNum(shorter.substring(1,shorter.length()));
            return (longest.less(shortest));
        }     
    }    
    /** Used only for unit testing. When run, should output only trues. */
    public static void main(String[] args) {
        // Test constructors
        BigNum test = new BigNum("123");
        System.out.println(test.toString().equals("123"));
        test = new BigNum(123);
        System.out.println(test.toString().equals("123"));
        test = new BigNum();
        System.out.println(test.toString().equals("0"));
        // Test addition
        BigNum a = new BigNum();
        BigNum b = new BigNum();
        BigNum c = a.add(b);
        System.out.println(c.toString().equals("0"));
        a = new BigNum("999");
        b = new BigNum("101");
        c = a.add(b);
        System.out.println(c.toString().equals("1100"));
        a = new BigNum("237468273643278");
        b = new BigNum("87326487236437826");
        c = a.add(b);
        // Test multiplication
        System.out.println(c.toString().equals("87563955510081104"));
        a = new BigNum("9999");
        b = new BigNum("9999");
        c = a.multiply(b);
        System.out.println(c.toString().equals("99980001"));
        a = new BigNum();
        b = new BigNum();
        c = a.add(b);
        System.out.println(c.toString().equals("0"));
        a = new BigNum("0");
        b = new BigNum("0");
        c = a.multiply(b);
        System.out.println(c.toString().equals("0"));
        a = new BigNum("9");
        b = new BigNum("10000");
        c = a.multiply(b);
        System.out.println(c.toString().equals("90000"));
        a = new BigNum("237468273643278");
        b = new BigNum("87326487236437826");
        c = a.multiply(b);
        System.out.println(c.toString().equals("20737270167368641268575749833628"));
        // Test less than
        a = new BigNum("1");
        b = new BigNum("1");
        System.out.println(a.less(b));
        a = new BigNum("10");
        b = new BigNum("1");
        System.out.println(a.less(b));
        a = new BigNum("5");
        b = new BigNum("1");
        System.out.println(a.less(b));
        a = new BigNum("1116");
        b = new BigNum("1115");
        System.out.println(a.less(b));
        a = new BigNum("1");
        b = new BigNum("10");
        System.out.println(a.less(b));
        a = new BigNum("1");
        b = new BigNum("100");
        System.out.println(a.less(b));
        a = new BigNum("1115");
        b = new BigNum("1116");
        System.out.println(a.less(b));
        a = new BigNum("237468273643278");
        b = new BigNum("87326487236437826");
        System.out.println(a.less(b));       
    }

}