import java.util.*;
import java.util.Random;
public class Main{
   public static void main(String[] args){
      RedBlack RBtree = new RedBlack();
      AVL AVLtree = new AVL();
      Random rn = new Random();
      int[] array = new int[1000000];
      double AVLstart,AVLa,AVLd,AVLr,AVLall,time;
      for(int i=0;i<array.length;i++)
         array[i]=i;
      AVLstart = System.currentTimeMillis(); 
      for(int i=0;i<array.length;i++) 
         AVLtree.insert(i);
      AVLa = System.currentTimeMillis() - AVLstart;
      AVLtree.removeAll();
      time = System.currentTimeMillis(); 
      for(int i=array.length;i>0;i--) 
         AVLtree.insert(i) ;
      AVLd = System.currentTimeMillis() - time;  
      AVLtree.removeAll(); 
      time = System.currentTimeMillis();
      for(int i=0;i<array.length;i++) 
         AVLtree.insert(rn.nextInt(50));
      AVLr = System.currentTimeMillis() - time;   
      AVLall=AVLa+AVLd+AVLr;
      
      double RBstart,RBa,RBd,RBr,RBall;
      for(int i=0;i<array.length;i++)
         array[i]=i;
      RBstart = System.currentTimeMillis(); 
      for(int i=0;i<array.length;i++) 
         RBtree.insert(i);
      RBa = System.currentTimeMillis() - RBstart;
      RBtree.removeAll();
      time = System.currentTimeMillis(); 
      for(int i=array.length;i>0;i--) 
         RBtree.insert(i) ;
      RBd = System.currentTimeMillis() - time;  
      RBtree.removeAll(); 
      time = System.currentTimeMillis(); 
      for(int i=0;i<array.length;i++) 
         RBtree.insert(rn.nextInt(50));
      RBr = System.currentTimeMillis() - time;   
      RBall=RBa+RBd+RBr;
      
      
      System.out.println("Ascending     |Descending |Random   |All"); 
      System.out.println("AVL");
      System.out.format("%f |%f |%f|%f%n",AVLa,AVLd,AVLr,AVLall);
      System.out.println("RedBlack");
      System.out.format("%f |%f |%f|%f%n",RBa,RBd,RBr,RBall);
         
   } 
}
