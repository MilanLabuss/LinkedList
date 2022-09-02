/**
 * A class that implements a list of objects using a linked list. 
 * Duplicated entries are allowed.
 * 
 * @author Jing Hua Ye
 * @date Oct/30/2021
 * @version 1.0
 */
 public class LinkedList<T> implements ListInterface<T>
 {
/* =============================================================================
                        HELPER CLASSES
   =============================================================================
 */
    /**
     * A class represents a node, which contains two parts - data and pointer 
     * to the next node
     */
     private class Node         //the node class
     {    //the data of a node
          private T data;
          //pointer to the next node
          private Node next;
   
          /**
           * To create a new node instance with a specific data
           * @param data the data of a specific node
           */
           public Node(T data)
           {
               this.data=data;      //pointing to the data variable and making it equal
           }
      
          /**
           * To create a new node instance with a specific data and pointer of 
           * the next node
           * @param data the data of a specific node
           * @param next the pointer of a specific next node
           */
           public Node(T data, Node next)
           {
               this.data=data;
               this.next=next;
           }

          /**
           * To get the data of this node 
           * @return the data of this Node
           */
           public T getData()
           {
               return data;
           }

          /**
           * To get the pointer of the next node 
           * @return the pointer of the next Node
           */
           public Node getNextNode()
           {
               return next;
           }

          /**
           * To set the data of this node 
           * @param data the data of this node 
           */
           public void setData(T data)
           {
               this.data=data;
           }   
          
          /**
           * To set the pointer of next node 
           * @param nextNode the pointer of the next node 
           */
           public void setNextNode(Node nextNode)
           {
               this.next=next;
           }

     }
/* ============================================================================
                        INSTANCE VARIABLES
   ============================================================================
 */
     //the header node
     private Node head;

     //the number of entries in the list
     private int numEntries;

/* ============================================================================
                        CONSTRUCTORS
   ============================================================================
 */
     /**
      * A default constructor 
      */
      public LinkedList()
      {
          Node head;            //creating the head
        numEntries=0;           //inintalising the number of entries to zero
      }
/* =============================================================================
                       INSTANCE METHODS
   =============================================================================
 */
/* ---------------------- Getters --------------------------------------------*/
    /**
     * Gets the
     * @return the integer number of entries currently in the list
     */
     public int getLength()
     { //to-complete
        return numEntries;
     }
/* ---------------------- Other Methods --------------------------------------*/
    /**
     * Add a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     */
     public void add(T newEntry)
     {


         //first checking if the linked list is empty
         if(head==null){
             head=new Node(newEntry);        //we make a new node and set it to the head of the list
             numEntries++;
         }
         else{          //if the list isnt empty
             Node newnode = new Node(newEntry);
             newnode.next = head;           //make the current head to the next of the node we want to add
             head=newnode;      //make the new node the head of the list
             numEntries++;
         }
     }
    
    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position are 
     * at the next higher position within the list.
     * The list's size is increased by 1.
     * 
     * @param newPosition an integer that specifies the desired position 
     * of the new entry
     * @param newEntry the object to be added as a new entry
     * @throws IndexOutOfBoundsException if either newPosition < 1 or 
     * mewPosition > getLength() - 1
     */
     public void add(int newPosition, T newEntry)
     {  if (newPosition >= 1 && newPosition <= numEntries + 1)
        {
            Node newNode = new Node(newEntry);     //making a new node to enter containing the data

            for(int i=0;i<newPosition-1;i++){
                //travelling through the list to reach the position
                head = head.next;
            }

            newNode.next=head.next;
            //updating the value
            head.next=newNode;

        }  
        else
           throw new IndexOutOfBoundsException("Illegal position given to add operation");
     }
    
    /**
     * Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given position
     * are at the next lower position within the list.
     * 
     * @param givenPosition an integer that indicates the position of the 
     * entry to be removed
     * @return A pointer to the removed entry
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()
     */
     public T remove(int givenPosition)    
     { if (givenPosition >= 1 && givenPosition <= numEntries)
       {  if (!isEmpty())
          {
                Node current = head;
                Node toDelete = null;       //making a null node

              for(int i=0;i<givenPosition-1;i++){
                  //travelling through the list to reach the position
                    current=current.next;
              }

              toDelete = current.next;
              current.next=toDelete.next;
             //now to decrement the number of entries
              numEntries--;

          }
          else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation");

       }
       return null;
     }

    /**
     * Replaces the entry at a given position in this list.
     * 
     * @param givenPosition an integer that indicates the position of the entry 
     * to be replaced
     * @param newEntry the object that will replace the entry at the position  
     * givenPosition
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()
     */
     public T replace(int givenPosition, T newEntry)
     { if (givenPosition >= 1 && givenPosition <= numEntries)
       {


//first remove
           Node current = head;
           Node toDelete = null;       //making a null node

           for(int i=0;i<givenPosition-1;i++){
               //travelling through the list to reach the position
               current=current.next;
           }

           toDelete = current.next;
           current.next=toDelete.next;
           //now to decrement the number of entries
           numEntries--;

 //then add


           Node newNode = new Node(newEntry);     //making a new node to enter containing the data

           for(int i=0;i<givenPosition-1;i++){
               //travelling through the list to reach the position
               head = head.next;
           }

           newNode.next=head.next;
           //updating the value
           head.next=newNode;

           numEntries++;

           return (T) head;


       }
       else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation"); 


     }
    
    /**
     * Retrieves the entry at a given position in this list.
     * 
     * @param givenPosition an integer that indicates the position of the 
     * desired entry
     * @return A pointer to the indicated entry
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()   
     */
     public T getEntry(int givenPosition) {
         T data;
         if (givenPosition >= 1 && givenPosition <= numEntries) {

            data= head.getData();
            head=head.next;

         } else
             throw new IndexOutOfBoundsException("Illegal position given to getEntry operation");

    return data;
     }
    /**
     * Sees whether this list contains a given entry.
     * 
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, otherwise false
     */
     public boolean contains(T anEntry)
     {
         Node current = head;
         for(int i=0;i<numEntries-1;i++){
             current=current.next;
             if (current.getData()==anEntry){
                 System.out.println("Found it: " + current.getData());
                    return true;
             }
         }
            System.out.println("Its not contained");
         return false;
     }

    /**
     * Sees whether this list is empty.
     * @return true if the list is empty, otherwise false
     */
     public boolean isEmpty()
     {
         if (numEntries==0 && head==null)
         {
             return true;

         }


         return false;
     }

    /**
     * Retrieves all entries that are in this list in the order in which they
     * occur in the list.
     * 
     * @return a newly allocation array of all the entries in the list. If the 
     * list is empty, the returned array is empty.
     */
     public T[] toArray()
     {   T[] result = (T[])new Object[numEntries];
         int index = 0;
         Node currentNode = head;
         while (index < numEntries && currentNode != null)
         {   result[index] = currentNode.getData();
             currentNode = currentNode.getNextNode();
             index++;
         }
         return result;
     }
    
    /**
     * To remove all nodes in the list
     */
     public void clear()
     {
         head=null;
         numEntries=0;
     }
/* =============================================================================
                       HELPER METHODS
   =============================================================================
 */
   /**
    * It traverse the whole list until we locate the node at the desired 
    * position within the list
    * @param givenPosition an integer that indicates the position of the 
    * desired entry
    */
    private Node getNodeAt(int givenPosition)
    { if (head != null && givenPosition >= 1 && givenPosition <= numEntries)
      {  Node currentNode = head;
         for (int counter = 1; counter < givenPosition; counter++)
            if (currentNode != null)
               currentNode = currentNode.getNextNode();
         return currentNode;
      }   
      return null;
    }
 }
