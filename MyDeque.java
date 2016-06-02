import java.util.*;
/** The <tt>MyDeque</tt> class represents a single ended queue that allows adding to
 *  both side but removal from the front. 
 *
 *  @author Erwin Seguinte
 *  @version Mar 7, 2015
 */
public class MyDeque <E>{

    // F i e l d s
    
   private Node<E> head;  //The List
   private Node<E> first;   //Beginning of List
   private Node<E> last;   //End of List

    // C o n s t r u c t o r s
   //@SuppressWarnings("unchecked") //Suppresses the warning for using Generic values
   
   /** Constructs a <tt>MyDeque</tt> from a non-null generic value and next node.
     *  @param data to be put into the deque 
     *  @param next hold the pointer to the next node,
     */
   public MyDeque(E data, Node<E> next){
      head = new Node<E>(data,next);
      first=head;
      last=head;
   }
   /** Constructs a <tt>MyDeque</tt> from a non-null generic value and sets next to null.
     *  @param data to put into the deque and sets the next item to null
     */
   public MyDeque(E data){
      this(data,null);
   }
   /** Constructs a <tt>MyDeque</tt> from empty parameters.
     *  
     */
   public MyDeque(){
      head=null;
      first=null;
      last=null;
   }
    // P u b l i c   M e t h o d s
   /** Add a generic value to the begginning of <tt>MyDeque</tt>.
     *  @param e the new data to be added
     */
   public void addFirst(E e){
      if(head==null){
         head = new Node<E>(e,null);
         first=head;
         last=head;
      }else{
         first=new Node<E>(e,head);
         head=first;
      }
   }
    /** Add a generic value to the end of <tt>MyDeque</tt>.
     *  @param e the new data to be added
     */
   public void addLast(E e){
      if(head==null){
         head = new Node<E>(e,null);
         first=head;
         last=head;
      }else{
         last.next=new Node<E>(e,null);
         last=last.next;
      }
   }
    /** Removes the first element at the begginning of <tt>MyDeque</tt>.
     *  @throws NoSuchElementException if MyDeque is empty
     *  @return the element that was revoved in the say type it came in
     */
   public E removeFirst(){
      if(head==null)
         throw new NoSuchElementException();
      E temp = head.data;
      head=head.next;
      first=head;
      return temp;
   }
    /** Get the first element in <tt>MyDeque</tt> that is not null.
     *  @throws NoSuchElementException if MyDeque is empty
     *  @return the first element of <tt>MyDeque</tt>;
     */
   public E getFirst(){
      if(head==null)
         throw new NoSuchElementException();
      return first.data;   
   }
    /** Get the last element in <tt>MyDeque</tt> that is not null.
     *  @throws NoSuchElementException if MyDeque is empty
     *  @return the last element of <tt>MyDeque</tt>;
     */
   public E getLast(){
      if(head==null)
         throw new NoSuchElementException();
      return last.data;   
   }
    /** Checks to see if <tt>MyDeque</tt> is empty.
     *  @return a boolean if <tt>MyDeque</tt> is empty or not.
     */
   public boolean isEmpty(){
      return (head==null);
   }    

   //U N I T  T E S T 
   /** Used only for unit testing. When run, should output only trues. */
   public static void main(String[] args) {
      MyDeque<String> deck = new MyDeque<String>("1");
      System.out.println(deck.getFirst().equals("1"));
      deck.addFirst("2");
      System.out.println(deck.getFirst().equals("2"));
      deck.addFirst("3");
      System.out.println(deck.getFirst().equals("3"));
      deck.addLast("4");
      System.out.println(!deck.isEmpty());
      System.out.println(deck.getLast().equals("4"));
      System.out.println(deck.removeFirst().equals("3"));
      System.out.println(deck.getFirst().equals("2"));
      System.out.println(deck.removeFirst().equals("2"));
      System.out.println(deck.getFirst().equals("1"));
      System.out.println(deck.removeFirst().equals("1"));
      System.out.println(deck.getFirst().equals("4"));
      System.out.println(deck.removeFirst().equals("4"));
      System.out.println(deck.isEmpty());
      try {
            deck.removeFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println("true");
        }
      try {
            deck.getFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println("true");
        }

   }
}        
class Node<E> {
   E data;
   Node<E> next;
    
   Node(E data, Node<E> next){
      this.data=data;
      this.next=next;
   }
}
 


