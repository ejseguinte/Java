import java.util.*;
import java.util.Random;
public class Heap{
   private Node[] heap;
   private int heapSize;
   private int size;
   
   public  Heap (int size){
      heap = new Node[size];
      heapSize = 0;
      this.size = size;
   }
   
   public int size(){
     return heapSize;  
   }
   
   public int valueOf(int index){
      return heap[index].key;
   }
   
   public void insert (Node node){
      int key = node.key;
      int pid = node.pid;
      insert(key,pid);
   }

   public void insert (int key , int pid){
      ++heapSize;
      heap[heapSize] = new Node(key,pid);
      if (heapSize>1 && heap[heapSize].key < heap[heapSize/2].key){
         up(heapSize);
      }
   }
   
   public Node remove (){
     Node temp = heap[1];
     if (heapSize>1){
      heap[1] = heap[heapSize];
     } else {
      heap[1] = null;
     }
     heapSize--;
     down(1);
     return temp; 
   }
   
   public void up(int k){
      while (k/2>0 && heap[k].key < heap[k/2].key){
         Node temp = heap[k];
         heap[k] = heap[k/2];
         heap[k/2] = temp;
         k/= 2;
      }
   }
   
   public void down(int k){
      if( heapSize == 0){
         return;
      }
      while ( k*2 < size-1 && heap[k].key > Math.min(heap[k*2].key, heap[(k*2)+1].key)){
         int key = Math.min(heap[k*2].key, heap[(k*2)+1].key);
         if (key == heap[k*2].key){
            Node temp = heap[k];
            heap[k] = heap[k*2];
            heap[k*2] = temp;
            k *= 2;
         }else if (key == heap[(k*2)+1].key){
            Node temp = heap[k];
            heap[k] = heap[(k*2)+1];
            heap[(k*2)+1] = temp;
            k =(k * 2) + 1;
         }
      }
   }
   
   public void singlePrint(Node node){
      System.out.printf("Nice = %2d", node.key);
      System.out.printf(" : PID = %3d\n", node.pid); 
   }
   
   public void print(){
     for(int i = 1; i < heapSize + 1; i++){
      System.out.printf("Idx: %3d ", i );
      singlePrint(heap[i]);
     }
   } 
  
   public static void main(String[] args){
      Heap test = new Heap(10);
      Random rn = new Random();
      
      int key = rn.nextInt(40);
      int pid = rn.nextInt(Integer.MAX_VALUE);
      System.out.println(key + ": " +pid);
      test.insert(key,pid);
      test.remove();
   }
}
class Node {
   public int key;
   public int pid;
   
   public Node (int key, int pid){
      this.key = key;
      this.pid = pid;
   }
   public Node (Node node){
      key = node.key;
      pid = node.pid;
   }
   
   public Node (){
      key = 0;
      pid = 0;
   }
   
}