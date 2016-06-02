import java.util.*;
import java.util.Random;
public class main {
   public static void main(String[] args){
      Heap start = new Heap(101);
      Heap finish = new Heap(101);
      Random rn = new Random();
      
      for(int i = 0; i < 100; i++){
         start.insert(rn.nextInt(40),i+1);   
      }
      start.print();
      for(int i = 0; i < 100; i++){
         finish.insert(start.remove());
      }
      System.out.println();
      finish.print();
   }
}