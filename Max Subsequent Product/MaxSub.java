import java.lang.Math;
import java.util.*;
public class MaxSub{
   public static list MaxSubPro(int[] arr){
      return MaxSubPro(arr,0,arr.length-1);
   }
   public static list MaxSubPro(int[] arr,int start, int end){
      if (arr.length == 0)
         return null;
      else if (start == end)
         return new list(start,end,arr[end]);   
      int center = (start+end)/2;
      list LCM = new list(center,center,arr[center]);
      list LCm = new list(center,center,arr[center]);
      list RCM = new list(center+1,center+1,arr[center+1]);
      list RCm = new list(center+1,center+1,arr[center+1]);
      int i,z;
      list maxL = new list(MaxSubPro(arr,start,center));
      int Lcross=arr[center];
      list maxR = new list(MaxSubPro(arr,center+1,end));
      int Rcross=arr[center+1];
      for(i = center-1; i >= start; i--){
         Lcross*=arr[i];
         if (Lcross>LCM.value){
            LCM.value = Lcross;
            LCM.start = i;
            LCM.end = center;}
         else if(Lcross<LCm.value){
            LCm.value = Lcross; 
            LCm.start = i;
            LCm.end = center;}
      }
      for(z=center+2; z<=end;z++){
         Rcross*=arr[z];
         if (Rcross>RCM.value){
            RCM.value = Rcross;
            RCM.start=center+1;
            RCM.end=z;
            }
         else if(Rcross<RCm.value){
            RCm.value = Rcross;
            RCm.start=center+1; 
            RCm.end=z;
            }
      }
      list max = new list(LCM.start,RCM.end,RCM.value*LCM.value);
      list min = new list(LCm.start,RCm.end,RCm.value*LCm.value);
      list finish = list.compare(maxL,maxR,min,max);
      return finish;
   }
   
   public static void main (String args[]){
      int arr[] = {0, -3, 0, -2, 0, 2, 0, -2};
      list finish = MaxSubPro(arr);
      System.out.println("The great subsequent product is "+finish.value+" from " + finish.start + " to " + finish.end +" in the array " + Arrays.toString(arr));
      int test[] = {4, -3, 5, -2, 1, 2, 6, -2};
      finish = MaxSubPro(test);
      System.out.println("The great subsequent product is "+finish.value+" from " + finish.start + " to " + finish.end +" in the array " + Arrays.toString(test));
      int test1[] = {0, 0, 0, 0, 3, 2, 6, 2};
      finish = MaxSubPro(test1);
      System.out.println("The great subsequent product is "+finish.value+" from " + finish.start + " to " + finish.end +" in the array " + Arrays.toString(test1));
      int test2[] = {0, 0, 0, 0, 0, 10, 0, 0};
      finish = MaxSubPro(test2);
      System.out.println("The great subsequent product is "+finish.value+" from " + finish.start + " to " + finish.end +" in the array " + Arrays.toString(test2));
      int test3[] = {};
      finish = MaxSubPro(test3);
      System.out.println("The great subsequent product is "+finish);
   }
}
class list{
   int start;
   int end;
   int value;
   public list(){
      start = 0;
      end = 0;
      value = 0;
   }
   public list(int a,int b,list c){
      start = a;
      end = b;
      value = c.value;
   }
   public list(int a,int b,int c){
      start = a;
      end = b;
      value = c;
   }
   public list(list change){
      start = change.start;
      end = change.end;
      value = change.value;
   }
      
   public static list compare(list first,list second,list third,list fourth){
      list end = new list();
      end.value=Math.max(third.value,Math.max(second.value,Math.max(fourth.value,first.value)));
      if (end.value == first.value){
         end.start=first.start;
         end.end=first.end;
      }else if(end.value == second.value){
         end.start=second.start;
         end.end=second.end;
      }else if(end.value == third.value){
         end.start=third.start;
         end.end=third.end;
      }else{
         end.start=fourth.start;
         end.end=fourth.end;
      }
      return end;
   }
   
   
}