/**Matt Bass Project 4 linkedList.java */



import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function


public class LinkedList<T> implements Iterable<T>{

        private class Node<T>
        {
            private Node<T> next;
            private T data;

            public Node(T item)
            {
                next = null;
                data = item;
            }

            //this is getThing just named getData returns value of data field of node
            public T getData()
            {
                return data;
            }

            //sets the next node refrence of the node
            public void setNext(Node<T> n)
            {
                next = n;
            }


            //returns the node that is refrenced in next in this node
            public Node<T> getNext()
            {
                return next;
            }
        }

        private Node<T> head;
        private Node<T> tail;
        private int size;

        // Return a new LLIterator pointing to the head of the list
        public Iterator<T> iterator() 
        {
            return new LLIterator(this.head);
        }
        
        //iterator for the Linkedlist
        private class LLIterator implements Iterator<T>
        {
            private Node<T> current;

            //the constructor for the LLIterator given the head of a list.
            public LLIterator(Node<T> head)
            {
                current = head;
            }

            //returns true if there are still values to traverse (if the current node reference is not null).
            public boolean hasNext()
            {
                if(current != null)
                {
                    return true;
                }
                return false;
            }

            //returns the next item in the list, which is the item contained in the current node.
            public T next()
            {
                //if List has a next value return it and set the current node in the iterator to next
                if(this.hasNext() == true)
                {
                    T result = current.getData();
                    current = current.getNext();
                    return result;
                }
                //if it does not return null meaning the end of the list has been reached and has no next
                else
                {
                    return null;
                }
            }


            //returns the current node of the itterator
            public Node<T> returnCurrent()
            {
                return current;
            }
        }
        
        
        //initilizes an empty linkedlist
        public LinkedList()
        {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        //clears the list (makes fields empty and size 0)
        public void clear()
        {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        //returns the size of the linked list
        public int size()
        {
            return this.size;
        }

        //adds node(item) to the begging of the list
        public void addFirst(T item)
        {
            
            Node<T> newNode = new Node<T>(item);

            //checks if list is empty
            if(this.head == null)
            {
                this.head = newNode;
                this.tail = newNode;
            }
            //if list does have somehting in it sets the new nodes next field to the current head
            //then makes the head the new node
            else
            {
                newNode.setNext(this.head);
                this.head = newNode;
            }
            
            //increment size by 1
            size++;
        }

        //appends the item at the end of the list.
        public void addLast(T item)
        {
            Node<T> newNode = new Node<T>(item); 
            
            //checks if list is empty
             if(this.head == null)
             {
                 this.head = newNode;
                 this.tail = newNode;
             }

             //else makem the node at the tail (end of the list) refrence next the new node 
             //and make the new node the tail refrence starting 
             else
             {
                 this.tail.setNext(newNode);
                 this.tail = newNode;
             }
            
             //increment size by 1
            size++;
        }

        //inserts the item at the specified poistion in the list.
        public void add(int index, T item)
        {
            //checkes to see if the index for the node being added is in the range of the Linkedlist
            //if it is not let the user know the index is out of range and return out of the method
            if(index < 0 || index > this.size)
            {
                System.out.println("Index for adding in not in range of Linkedlist");
                return;
            }
            //if the index is 0 just use the addFirst method
            if(index == 0)
            {
                this.addFirst(item);
            }
            //if the index is at the end of the list use addLast method
            else if(index == this.size)
            {
                this.addLast(item);
            }
            //if not proceed into main part of function
            else
            {
                //after checking index make the new node to be added
                // and a refrene named temp for the node before the index initialy to head b/c of loop
                Node<T> newNode = new Node<T>(item);
                Node<T> temp = this.head;
                
                //moving refrence temp to wanted location of new node
                for(int i = 1; i < index; i++)
                {
                    temp = temp.getNext();
                }
                //add new node to list
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
                //increment size by 1
                this.size++;
            }
        } 

        //removes the item at the specified position in the list returns it and removes the node
        public T remove(int index)
        {
            //checkes to see if the index for the node being removed is in the range of the Linkedlist
            //if it is not let the user know the index is out of range and return null out of the method
            if(index < 0 || index > this.size)
            {
                System.out.println("Index for removing in not in range of Linkedlist");
                return null;
            }
            //if index is in range first create itterator and int i for a ticker
            //and a previous node refrence and T to hold tthe result
            LLIterator linkedITT = new LLIterator(this.head);
            int i = 0;
            Node<T> previous = null;
            T result;
            //while we can keep going to a next node in range and int I hasnt reached index
            while(linkedITT.hasNext() && i != index)
            {  
                previous = linkedITT.returnCurrent();
                linkedITT.next();
                i++;
            }
           
            Node<T> current = linkedITT.returnCurrent();
            result = linkedITT.next();

            //removing first element of list (head)
            if(current == this.head)
            {
                this.head = this.head.getNext();
            }
            //if at the end
            else if(current == this.tail)
            {
                this.tail = previous;
                previous.setNext(null);
            }
            //else if in the middle
            else
            {
                previous.setNext(current.getNext());
            }
            //decrease size by 1
            size--;
            
            return result;
        }
        //returns an ArrayList of the list contents in order.
        public ArrayList<T> toArrayList()
        {
            ArrayList<T> arrList = new ArrayList<T>(); ;
            
        
            for(T data : this)
            {
                arrList.add(data);
            }
            return arrList;
        }
        //returns an ArrayList of the list contents in shuffled order.
        public ArrayList<T> toShuffledList()
        {
            ArrayList<T> arrList = new ArrayList<T>(); ;
            
        
            for(T data : this)
            {
                arrList.add(data);
            }
            
            Collections.shuffle(arrList);
            return arrList;
        }
        
        public static void main(String[] args)
        {
            LinkedList<Integer> llist = new LinkedList<Integer>();

		    // add ten numbers to the list, in order
            for(int i=0;i<5;i++) 
            {
				llist.addFirst(i);
		    }
            System.out.println("There are " + llist.size() + " lights");
            // execute a foreach loop
            for(Integer q: llist) 
            {
                System.out.println("value: " + q);
            }

            ArrayList<Integer> testArray = llist.toArrayList();
            System.out.println(testArray.toString());

            ArrayList<Integer> testShuffleArray = llist.toShuffledList();
            System.out.println(testShuffleArray.toString());
        }

        

}


