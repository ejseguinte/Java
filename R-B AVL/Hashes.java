import java.util.*;
public class Hashes{
   public static final int TSIZE = 23; //size = 1901
   /* This is the class driver for creating and 
    * timing the different hash types: linked 
    * list, linear probing, quadratic probing,
    * and double hashing. This will time three
    * different functions add, find, and remove. 
    */ 
   public static void main(String [] args){
      Random rn = new Random();
      int[] array = new int[20]; //size = 8192
      for ( int i = 0; i < array.length; i++){
         int temp = rn.nextInt(65536); // Creates a 16-bit integer
         if(!(Arrays.asList(array).contains(temp)))
            array[i] = temp;
      }  
      linkedTable linkTable = new linkedTable(TSIZE);
      linearTable lTable = new linearTable(TSIZE);
      quadTable qTable = new quadTable(TSIZE);
      doubleTable dTable = new doubleTable(TSIZE);
      
      for ( int i = 0; i < array.length; i++){
         linkTable.insert(array[i]);
      }
      
      for ( int i = 0; i < array.length; i++){
         lTable.insert(array[i]);
      }
      
      for ( int i = 0; i < array.length; i++){
         qTable.insert(array[i]);
      }
   }  
}

class linkedTable {
   private LinkedList<Integer> [] table;
   
   public linkedTable(int size) {
      table = new LinkedList[size];
      for (int i = 0; i < size; i++){
         table[i] = new LinkedList<Integer>();
      }
   }
   
   public void insert (int val) {
      int hashVal = val % table.length;
      table[hashVal].add(val);
   }
   
}

class linearTable {
   private hashTable[] table;
   
   public linearTable(int size) {
      table = new hashTable[size];
      for (int i = 0; i < size; i++){
         table[i] = new hashTable();
      }
   }

   public void insert (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      for (int i = 0; i < table.length; hashVal = (( realHash + ++i)%table.length) ){
         if(table[hashVal].val == -.1){
            table[hashVal].add(val,realHash);
            if (i > 0){
               if(table[realHash].jumps > 0)
                  table[realHash].jumps = i;
               else
                  table[realHash].jumps = -(i);   
            } 
            return;
         }else if (hashVal == realHash){
            table[hashVal].add(val,realHash,table[hashVal].jumps*-1);
            return;      
         }
      }
      System.out.println("No more space");
   }
}

class quadTable {
   private hashTable[] table;
   
   public quadTable(int size) {
      table = new hashTable[size];
      for (int i = 0; i < size; i++){
         table[i] = new hashTable();
      }
   }

   public void insert (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      for (int i = 0; i < table.length; hashVal = (( realHash + (++i*i))%table.length) ){
         if(table[hashVal].val == -.1){
            table[hashVal].add(val,realHash);
            if (i > 0){
               if(table[realHash].jumps > 0)
                  table[realHash].jumps = i;
               else
                  table[realHash].jumps = -(i);   
            } 
            return;
         }else if (hashVal == realHash){
            table[hashVal].add(val,realHash,table[hashVal].jumps*-1);
            return;      
         }
      }
      System.out.println("No more space");
   }
      
  }

class doubleTable {
   private int[] table;
   
   public doubleTable(int size) {
      table = new int[size];
   }
   
   public void insert (int val) {
      int hashVal = val % table.length;
      
   }
}

class hashTable {
   public double val;
   public int key;
   public int jumps;
   
   public hashTable (){
      val = -.1;
      key = 0;
      jumps = 0;
   }
   
   public void add (int val,int key){
      this.val   = (double) val;
      this.key   = key;
   }
   
   public void add (int val,int key, int jumps){
      this.val   = (double) val;
      this.key   = key;
      this.jumps = jumps;
   }
   
   public void remove (){
      this.val   = -.1;
      this.key   = 0;
      if(jumps > 0)
         this.jumps = jumps * -1;
      else
         this.jumps = 0;   
   }
  
}