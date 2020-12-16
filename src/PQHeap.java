

/*
***Matt Bass***
PQHeap.java

Fall 2020
CS 231 Project 8
*/

import java.lang.Math;

import java.util.Comparator;


//Why cant I make it an object
public class PQHeap<T>{

        private Object[] heap;
        //number of elements in the heap.
        private int size;
        private int maxSize;
        private Comparator<T> comp;

        
        
        
        // a constructor that initializes the empty heap, sets the size to zero, 
        // and stores the comparator.
        //initilizes max size to 256
        public PQHeap(Comparator<T> comparator)
        {

            this.maxSize = 256;
            this.size = 0;
            this.comp = comparator;
            //add 1 because index 0 is filled with null
            this.heap = new Object[this.maxSize + 1];
            this.heap[0] = null;

        }
        //returns the number of elements in the heap.
        public int size()
        {
            return this.size;
        } 

        // Returns position of parent
        private int parent(int position)
        {
            return (position / 2);
        }
        
        // return left child
        private int leftChild(int position)
        {
            return (2 * position);
        }
        // returns the right child
        private int rightChild(int position)
        {
            return( (2 * position) + 1);
        }


        // Returns true of given node is leaf
        private boolean isLeaf(int position)
        {
            if (position >= (size / 2) && position <= size) 
            {
                return true;
            }
            return false;
        }


        //helper function to swap values arround in the heap
        private void swap(int firstPosition, int secondPosition)
        {
            Object temp = this.heap[firstPosition];
            this.heap[firstPosition] = this.heap[secondPosition];
            this.heap[secondPosition] = temp; 
        }



        //helper function to re"max-heapify" the heapp array
        private void maxHeapify(int position)
        {
            int left = leftChild(position);
            int right = rightChild(position);
            // (this.comp.compare((T)this.heap[rightChild(position)], this.size) < 0)
            while(left <= size)
            {
                Integer maxIndex = left;
                if ((rightChild(position) < this.size) && (this.comp.compare((T)this.heap[rightChild(position)], (T)this.heap[leftChild(position)]) > 0))
                {
                    maxIndex = right;
                }
                
                if (this.comp.compare((T)this.heap[position], (T)this.heap[maxIndex]) < 0)
                {
                    swap(position, maxIndex);
                    position = maxIndex;
                    left = leftChild(position);
                    right = rightChild(position);
                }
                else
                {
                    return;
                }
            } 
        }

        


        public void add(Object obj)
        {
            //heck if heap needs to be expanded
            if(this.size >= this.maxSize / 2)
            {
                
                this.maxSize = this.maxSize * 2;
                Object[] newHeap = new Object[this.maxSize];

                

                
                for(int i = 0; i < this.heap.length; i++)
                {
                    newHeap[i] = this.heap[i];
                }

                this.heap = newHeap;
                
            }
            
            this.heap[++this.size] = obj;
            
            // Traverse up and fix violated property
            int current = this.size;
            if(this.size > 1)
            {
                while ( (parent(current) >= 1) && (this.comp.compare((T)this.heap[current], (T)this.heap[parent(current)] ) > 0))
                {
                    
                   
                    swap(current, parent(current));
                     
                    current = parent(current);
                   
                      
                }
            }
            
            
        }



        // Remove an hight priority object from the pariority heap
        public T remove()
        {
            if(this.size >= 1)
            {
                
                Object popped = this.heap[1];
                this.heap[1] = this.heap[this.size--];
                //this.heap[this.size + 1] = popped;
                maxHeapify(1);
                // System.out.println("Remove 2 Heap:  " + toString());
                return ((T)popped);
            }
            else
            {
                return null;
            }
            
        }



         // to string methjod
         public String toString()
         {
            String str = "[";
            for(Object obj : this.heap)
            {
                str += obj + ",";
            }
            return str;
         }

        

}