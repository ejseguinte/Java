import java.util.*;
public class AVL{
   private AVLNode root;
   
   public AVL (){
      root = null;
   }
   
   public int height(AVLNode k){
      if (k==null)
         return -1;
      else 
         return Math.max(height(k.left),height(k.right))+1;    
   }
   
   public void insert (int data){
      root =insert(data,root);   
   }
   
   public AVLNode insert (int data,AVLNode k){
      if( k == null)
         k = new AVLNode(data);
      else if (data<k.data){
         k.left=insert(data,k.left);
         if(height(k.left)-height(k.right) == 2){
            if(data<k.left.data)
               k=rotateToTheLeft(k);
            else 
               k=doubleRotateToTheLeft(k);  
         }
      }
      else if (data>k.data){
         k.right=insert(data,k.right);
         if(height(k.right)-height(k.left) == 2){
            if(data>k.right.data)
               k=rotateToTheRight(k);
            else 
               k=doubleRotateToTheRight(k);  
         }
      }
      k.height = Math.max(height(k.left),height(k.right))+1;
      return k;   
   }
   
   public AVLNode rotateToTheLeft (AVLNode k2){
      AVLNode k1= k2.left;
      k2.left =k1.right;
      k1.right=k2;
      k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
      k1.height = Math.max( height( k1.left ), k2.height ) + 1;
      return k1;
   }
   public AVLNode doubleRotateToTheLeft (AVLNode k3){
      k3.left= rotateToTheRight(k3.left);
      return rotateToTheLeft(k3);
   }
   public AVLNode rotateToTheRight (AVLNode k1){
      AVLNode k2=k1.right;
      k1.right=k2.left;
      k2.left=k1;
      k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
      k2.height = Math.max( height( k2.right ), k1.height ) + 1;
      return k2;
   }
   public AVLNode doubleRotateToTheRight (AVLNode k1){
      k1.right = rotateToTheLeft( k1.right );
      return rotateToTheRight( k1 );
   }
   public void removeAll(){
      root=null;
   }
   public static void main(String [] args){
      AVL AVLtree = new AVL();
      
      AVLtree.insert(1);
      AVLtree.insert(2);
      AVLtree.insert(3);
      AVLtree.insert(4);
      AVLtree.insert(5);
      AVLtree.insert(100);
      AVLtree.insert(99);
      AVLtree.insert(98);
      AVLtree.insert(97);
      AVLtree.insert(96);
      AVLtree.insert(25);
      AVLtree.insert(30);
      AVLtree.insert(35);
      AVLtree.insert(40);
      AVLtree.insert(45);
      
   }   
}
class AVLNode{
   int data;
   AVLNode left;
   AVLNode right;
   int height;
   
   public AVLNode(){
      left = null;
      right = null;
      data=0;
      height=0;
   }
   public AVLNode(int x){
      left = null;
      right = null;
      data=x;
      height=0;
   }
}