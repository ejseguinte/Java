import java.util.*;
import java.util.Random;

public class Sort{
   
   public static List mergeSort(List<Integer> list){
      return mergeSort(list,0,list.size()-1);
   } 
   
   public static List mergeSort(List<Integer> list,int low, int high){
      if (low < high){
         int center = (low + high) /2;
         mergeSort(list,low,center-1);
         mergeSort(list,center,high);
         merge(list,low,center,high); 
      }
      return list;
   }
   
   public static void merge(List<Integer> list,int low, int center, int high){
      List<Integer> helper = new ArrayList<Integer>();
      for(int i = low; i <= high; i++){
         helper.add(list.get(i));
      }
      int i = low, j = center + 1, k = low;
      while(i <= center && j <= high){
         if(helper.get(i) <= helper.get(j)){
            list.set(k,helper.get(i));
            i++;
         }else{
            list.set(k,helper.get(j));
            j++;
         }
         k++;
      }
      while(i<=center){
         list.set(k,helper.get(i));
         k++;
         i++;
      }   
    }
   
   public static List quickSort(List<Integer> list){
      return quickSort(list,0,list.size()-1);
   }
   public static List quickSort(List<Integer> list,int low, int high){
      int idx = partition(list, low, high);
      if (low < idx - 1){
         quickSort(list,low,idx-1);
      }
      if(idx < high){
         quickSort(list,idx,high);
      }
      return list;
   }
   
   public static int partition(List<Integer> list, int low, int high){
      int i=low,j=high;
      int temp;
      int pivot = list.get(low);//list.get((low+high)/2);
      while(i<=j){
         while(list.get(i) < pivot){
            i++;
         }
         while(list.get(j) > pivot){
            j--;
         }
         if(i<=j){
            temp = list.get(i);
            list.set(i,list.get(j));
            list.set(j,temp);
            i++;
            j--;
          }
         };
      return i;
   }
  
   public static void main(String[] args){
      List<Integer> list = new ArrayList<Integer>();
      Random rn = new Random();
      
      for (int i = 0; i < 10 ; i++){
         int temp = rn.nextInt(99)+1;
         while(list.contains(temp)){
            temp = rn.nextInt(99)+1;
         }
            list.add(temp);
      }
      for(int i = 0; i < list.size() ; i++){
         if(i == list.size()-1){
            System.out.printf("%3d ",list.get(i));
         }else{
            System.out.printf("%3d ,",list.get(i));
         }
      }
      
      List<Integer> order =mergeSort(list);
      System.out.println();
      for(int i = 0; i < order.size() ; i++){
         if(i == order.size()-1){
            System.out.printf("%3d ",order.get(i));
         }else{
            System.out.printf("%3d ,",order.get(i));
         }
      }
   }
}