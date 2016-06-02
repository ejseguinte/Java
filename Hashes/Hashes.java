import java.util.*;
public class Hashes{
   public static final int TSIZE = 8209; //size = 1901
   /* This is the class driver for creating and 
    * timing the different hash types: linked 
    * list, linear probing, quadratic probing,
    * and double hashing. This will time three
    * different functions add, find, and remove. 
    */ 
   public static void main(String [] args){
      Random rn = new Random();
      float [] times = new float[12];
      float start, end ; 
      int x = 0;
      int[] array = new int[8192]; //size = 8192
      for ( int i = 0; i < array.length; i++){
         int temp = rn.nextInt(65536); // Creates a 16-bit integer
         if(!(Arrays.asList(array).contains(temp)))
            array[i] = temp;
      }  
      linkedTable linkTable = new linkedTable(TSIZE);
      linearTable lTable = new linearTable(TSIZE);
      quadTable qTable = new quadTable(TSIZE);
      doubleTable dTable = new doubleTable(TSIZE); //Change 
      
      start = System.nanoTime(); 
      for ( int i = 0; i < array.length; i++){
         linkTable.insert(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = 0; i < array.length; i++){
         lTable.insert(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = 0; i < array.length; i++){
         qTable.insert(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = 0; i < array.length; i+=2){ //change back
         dTable.insert(i);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = 0; i < 1000; i++){ //Change array.length to 1000
               linkTable.remove(array[rn.nextInt(array.length)]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = 0; i < 1000; i++){ //Change array.length to 1000
               lTable.remove(array[rn.nextInt(array.length)]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;

      start = System.nanoTime();
      for ( int i = 0; i < 1000; i++){ //Change array.length to 1000
               qTable.remove(array[rn.nextInt(array.length)]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;   
      
      start = System.nanoTime();
      for ( int i = 0; i < 1000; i++){ //Change array.length to 1000
               dTable.remove(array[rn.nextInt(array.length)]); 
      }
      end = System.nanoTime(); 
      times[x++]=end-start; 
      
      start = System.nanoTime();
      for ( int i = array.length - 1; i > 0; i--){
         linkTable.find(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;   
      
      start = System.nanoTime();
      for ( int i = array.length - 1; i > 0; i--){
         lTable.find(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = array.length - 1; i > 0; i--){
         qTable.find(array[i]);
      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      start = System.nanoTime();
      for ( int i = array.length - 1; i > 0; i--){
         dTable.find(array[i]);

      }
      end = System.nanoTime(); 
      times[x++]=end-start;
      
      System.out.format ("Type   | %-18s| %-18s| %-18s| %-18s|%n","Insert","Remove 1000","Find","Total");
      System.out.format ("Linked | %18.2f| %18.2f| %18.2f| %18.2f|%n",times[0],times[4],times[8],(times[0]+times[4]+times[8]));
      System.out.format ("Linear | %18.2f| %18.2f| %18.2f| %18.2f|%n",times[1],times[5],times[9],(times[1]+times[5]+times[9]));
      System.out.format ("Quad   | %18.2f| %18.2f| %18.2f| %18.2f|%n",times[2],times[6],times[10],(times[2]+times[6]+times[10]));
      System.out.format ("Double | %18.2f| %18.2f| %18.2f| %18.2f|%n",times[3],times[7],times[11],(times[3]+times[7]+times[11]));
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
   
   public int find (int val){
     int hashVal = val % table.length;
     int idx = table[hashVal].indexOf(val); 
     if (idx >= 0){
         //System.out.println(val + " is in index " + hashVal + " of the table and index " + idx + " of the Linked List");
         return idx;
     }else{ 
         //System.out.println("Not in table");
         return -1; 
      }     
   }
   
   public int remove (int val){
      int hashVal = val % table.length;
      int idx = find(val);
      if (idx != -1){
         table[hashVal].remove(idx);
         return idx;  
      }else
         return -1;
            
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
               if(table[realHash].jumps >= 0)
                  table[realHash].jumps = i;
               else
                  table[realHash].jumps = -(i);   
            } 
            return;
         }else if (hashVal == realHash && table[hashVal].val == -.1){
            table[hashVal].add(val,realHash,table[hashVal].jumps*-1);
            return;      
         }
      }
      System.out.println("No more space");
   }
   
   public int find (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      int jumps = table[hashVal].jumps;
      int i = 0;
      if (jumps < 0)
         jumps*=-1;  
      for (i=i; i < table.length && jumps >= 0; hashVal = (( realHash + ++i )%table.length) ) {
         if(table[hashVal].val == val){
            //System.out.println(val + " is in the index " + hashVal + " of the table"); 
            return hashVal;
         }     
      }
      //System.out.println("Not in table");
      return -1;  
   }
   
   public int remove (int val){
      int hashVal = val % table.length;
      int idx = find(val);
      if (idx == -1)
         return -1;
      else {   
         table[idx].remove();
         if ((idx == hashVal) && (table[hashVal].jumps > 0)){
            table[hashVal].jumps *= -1;
         }else if(table[hashVal].jumps < 0){
            table[hashVal].jumps+=1;
         }else if(table[hashVal].jumps != 0){
            table[hashVal].jumps-=1;    
         }
      }
      return idx;   
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
               if(table[realHash].jumps >= 0)
                  table[realHash].jumps = i;
               else
                  table[realHash].jumps = -(i);   
            } 
            return;
         }else if (hashVal == realHash && table[hashVal].val == -.1 ){
            table[hashVal].add(val,realHash,table[hashVal].jumps*-1);
            return;      
         }
      }
     System.out.println("No more space");
   }
   
   public int find (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      int jumps = table[hashVal].jumps;
      if (jumps < 0)
         jumps*=-1;
      for (int i = 0; i < table.length && jumps >= 0; hashVal = (( realHash + (++i * i) )%table.length) ) {
         if(table[hashVal].val == val){
            //System.out.println(val + " is in the index " + hashVal + " of the table"); 
            return hashVal;
         }     
      }
      //System.out.println("Not in table");
      return -1;  
   }   
   
   public int remove (int val){
      int hashVal = val % table.length;
      int idx = find(val);
      if (idx == -1)
         return -1;
      else {   
         table[idx].val = -.1;
         if ((idx == hashVal) && (table[hashVal].jumps > 0)){
            table[hashVal].jumps *= -1;
         }else if(table[hashVal].jumps < 0){
            table[hashVal].jumps+=1;
         }else if(table[hashVal].jumps != 0){
            table[hashVal].jumps-=1;    
         }
      }
      return idx;   
   }     
  }

class doubleTable {
   private hashTable[] table;
   public static final int HASH = 7; 
   
   public doubleTable(int size) {
      table = new hashTable[size];
      for (int i = 0; i < size; i++){
         table[i] = new hashTable();
      }
   }

   public void insert (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      for (int i = 0; i < table.length; 
         hashVal = (hashVal + (++i)*( HASH - hashVal % HASH ))%table.length){ //Check later
         if(table[hashVal].val == -.1){
            table[hashVal].add(val,realHash);
            if (i > 0){
               if(table[realHash].jumps >= 0)
                  table[realHash].jumps = i;
               else
                  table[realHash].jumps = -(i);   
            } 
            return;
         }else if (hashVal == realHash && table[hashVal].val == -.1 ){
            table[hashVal].add(val,realHash,table[hashVal].jumps*-1);
            return;      
         }
      }
     System.out.println("No more space");
   }
   
   public int find (int val) {
      int hashVal = val % table.length;
      int realHash = hashVal;
      int jumps = table[hashVal].jumps;
      if (jumps < 0)
         jumps*=-1;
      for (int i = 0; i < table.length && jumps >= 0; 
         hashVal = (hashVal + (++i)*( HASH - hashVal % HASH ))%table.length) { //Change
         i++;
         if(table[hashVal].val == val){
            //System.out.println(val + " is in the index " + hashVal + " of the table"); 
            return hashVal;
         }     
      }
      //System.out.println("Not in table");
      return -1;  
   }   
   
   public int remove (int val){
      int hashVal = val % table.length;
      int idx = find(val);
      if (idx == -1)
         return -1;
      else {   
         table[idx].val = -.1;
         if ((idx == hashVal) && (table[hashVal].jumps > 0)){
            table[hashVal].jumps *= -1;
         }else if(table[hashVal].jumps < 0){
            table[hashVal].jumps+=1;
         }else if(table[hashVal].jumps != 0){
            table[hashVal].jumps-=1;    
         }
      }
      return idx;   
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
   }
  
}