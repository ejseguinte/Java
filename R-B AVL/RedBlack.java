import java.util.*;
public class RedBlack{
   public static final boolean RED   = true;
   public static final boolean BLACK = false;
   private Node root;
   public boolean isRed(Node k){
      if (k==null)
         return false;
      return k.color == RED;   
   }
   public Node flipColors(Node k){
      k.color = RED;
      k.left.color= BLACK;
      k.right.color = BLACK;
      return k;
   }

   public Node rotateLeft(Node k){
      Node x = k.right;
      k.right = x.left;
      x.left=k;
      x.color = k.color;
      k.color = RED;
      return x;
   }   
   public Node rotateRight(Node k){
      Node x = k.left;
      k.left = x.right;
      x.right=k;
      x.color = k.color;
      k.color = RED;
      return x;
   }  
   
   public void insert (int data){
      root =insert(data,root); 
      if (root.color!= BLACK)
         root.color=BLACK;  
   }
   
   public Node insert(int data,Node k){
      if(k == null)
         return new Node(data,true,null,null);
      if( data < k.data){
         if(k.left != null){
            k.left= insert(data,k.left);
         }else{
            k.left=new Node(data,true,null,null);
         }
      }else if(data>k.data){
         if (k.right !=null){
            k.right= insert(data,k.right);
         }else{
            k.right=new Node(data,true,null,null);
         }
      }
      if(isRed(k.right)&&!isRed(k.left))
         k=rotateLeft(k);
      if(isRed(k.left)&& isRed(k.left.left))
         k=rotateRight(k);
      if(isRed(k.left)&& isRed(k.right))
         k=flipColors(k);
      return k;           
   }  
   
   public void removeAll(){
      root=null;
   }
   
   public static void main(String [] args){
      RedBlack RBtree = new RedBlack();
      
      RBtree.insert(1);
      RBtree.insert(2);
      RBtree.insert(3);
      RBtree.insert(4);
      RBtree.insert(5);
      RBtree.insert(100);
      RBtree.insert(99);
      RBtree.insert(98);
      RBtree.insert(97);
      RBtree.insert(96);
      RBtree.insert(25);
      RBtree.insert(30);
      RBtree.insert(35);
      RBtree.insert(40);
      RBtree.insert(45);
      
   }   


}
class Node{
   int data;
   Node left;
   Node right;
   boolean color;
   
   public Node(){
      left = null;
      right = null;
      data=0;
      color=RedBlack.BLACK;
   }
   public Node(int x,boolean type,Node left,Node right){
      left = left;
      right = right;
      data=x;
      color=type;
   }
}