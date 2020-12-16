/*
***Matt Bass***
Hashmap.java

Fall 2020
CS 231 Project 7
*/
import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;

//holds generic objecs k and v standing for key and value objects
public class Hashmap<K,V> implements MapSet<K,V>{

    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //protected node Class for Hash Map
    //Name HashNode
    
    protected class HashNode
    {

	    //intializing private field KeyValuePair to hold the data (keyval) at this node
        private KeyValuePair<K,V> data;

        //initializing Private HashNode to hold the next Hashnode it is connected too
        private HashNode next;
    

        public HashNode(K key, V value)
        {
			this.data = new KeyValuePair<K,V>(key, value);
			this.next = null;
		}

        public String toString()
        {

			return (String) this.data.getKey() + "=" + (String) this.data.getValue();
        }
        
       
       
        //getters for key value and next
        public K getKey()
        {
            return this.data.getKey();
        }

        public V getValue()
        {
            return this.data.getValue();
        }

        public HashNode getNext()
        {
            return this.next;
        }

        public KeyValuePair<K,V> getData()
        {
            return this.data;
        }


        //setters for HashNode
        public void setKey(K key)
        {
            this.data.setKey(key);
        }

        public void setValue(V value)
        {
            this.data.setValue(value);
        }

        public void setNext(HashNode node)
        {
            this.next = node;
        }


        //let known if node has next
        public boolean hasNext()
        {
            if(this.next == null)
            {
                return false;
            }
            return true;
        }
	}

 



     //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //Fields and Construtor for Hashmap 

    //question can i majke initial variable outside of constructor
    ////QUESTION QUESTION QUESTION QUESTION QUESTION QUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTION
    //////QUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTIONQUESTION
	// private Object[] hashTable = null;
	// private int tableSize = 128;
    // private int nodesInTable = 0;
    
    // //intializing private field for a comparator object of type K
    //  private Comparator<K> comp;


    
	private int tableSize;
    private int nodesInTable;
    private Object[] hashTable;
    
    //intializing private field for a comparator object of type K
    private Comparator<K> comp;


    // Hashmap constructor that starts with default size hash table 128
    //qQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTIONqQUESTION
    //do i need to specify comparator type
    public Hashmap(Comparator<K> incomp) 
    {
        this.comp = incomp;
        this.tableSize = 256;
        this.nodesInTable = 0;
        this.hashTable = new Object[this.tableSize];
    }

    // Hashmap constructor that starts with the suggecsted capacity hash table
    public Hashmap( Comparator<K> incomp, int capacity ) 
    {
        this.comp = incomp;
        this.tableSize = capacity;
        this.nodesInTable = 0;
        this.hashTable = new Object[this.tableSize];
    }

    public HashNode createHashNode(K key, V value)
    {
        return new HashNode(key,value);
    }


    public V put(K key, V value)
    {
		
        //heck if hash table needs to be expanded
        if(this.nodesInTable >= this.tableSize / 3)
        {

			ArrayList<KeyValuePair<K,V>> info = this.entrySet();

			this.tableSize = this.tableSize * 3;
			this.hashTable = new Object[this.tableSize];
			this.nodesInTable = 0;

            for(int i = 0; i < info.size(); i++)
            {
				this.put(info.get(i).getKey(), info.get(i).getValue());
			}
		}


        //main put function
		int position = this.hash(key);

        HashNode current = (HashNode) this.hashTable[position];
        if (current == null)
        {
            hashTable[position] = this.createHashNode(key,value);
            this.nodesInTable++;
            return null;
		}
        else
        {
            while(current.getNext() != null && this.comp.compare(current.getKey(), key) != 0)
            {
				current = current.getNext();
			}
            if (this.comp.compare(current.getKey(), key) == 0)
            {
                V oldVal = current.getValue();
                current.setValue(value);
                return oldVal;
                
            }
            else if(current.getNext() == null)
            {
                current.setNext(this.createHashNode(key,value));
                this.nodesInTable++;
                return null;
            }
            return null;
        }
        
	}

    
    
    
    private int hash(K key)
    {
		return Math.abs(((String)key.toString()).hashCode()) % this.tableSize;
	}

    
    
    
    public String toString()
    {
		String result = "";

        for(int i = 0; i < tableSize; i++)
        {
            String hashPosChain = "[ ";
            HashNode node = (HashNode) hashTable[i];
            int chainPos = 0;
            while (node != null)
            {
                if(chainPos == 0)
                {
                    hashPosChain += node.getKey() +"="+ node.getValue();
                }
                else
                {
                    hashPosChain += " , "+ node.getKey() +"="+ node.getValue();
                }
                
                node = node.next;
                chainPos++;
            }
            //+= vs =
            hashPosChain = hashPosChain + " ]";
            result += hashPosChain;
		}
		return result;
    }
    


    public String toStringNoEmpties()
    {
		String result = "";

        for(int i = 0; i < tableSize; i++)
        {
            
            HashNode node = (HashNode) hashTable[i];

            if(node != null)
            {
                String hashPosChain = "[ ";
                int chainPos = 0;
                while (node != null)
                {
                    if(chainPos == 0)
                    {
                        hashPosChain += node.getKey() +"="+ node.getValue();
                    }
                    else
                    {
                        hashPosChain += " , "+ node.getKey() +"="+ node.getValue();
                    }
                    
                    node = node.next;
                    chainPos++;
                }
               
                hashPosChain = hashPosChain + " ]";
                result += hashPosChain;
            }
            
		}
		return result;
	}

    
    
    
    
    public boolean containsKey(K key)
    {

		int position = hash(key);

		HashNode current = (HashNode) hashTable[position];

        if (current == null)
        {
			return false;
		}
		else{

            while(current.next != null && this.comp.compare(current.getKey(), key) != 0)
            {
				current = current.getNext();
			}
			if(this.comp.compare(current.getKey(), key) == 0){
				return true;
			}
		}
		return false;
	}

    public V get(K key)
    {

		int position = hash(key);

		HashNode node = (HashNode) hashTable[position];

        if (node == null)
        {
			return null;
		}
        else 
        {
			
            while(node.getNext() != null && this.comp.compare(node.getKey(), key) != 0)
            {
				node = node.getNext();
			}

            if(this.comp.compare(node.getKey(), key) == 0)
            {
				return node.getValue();
			}
		}
		return null;
	}

    public HashNode find(K key)
    {

		int position = hash(key);

		HashNode node = (HashNode) hashTable[position];

        if (node == null)
        {
			return null;
		}
		else {
			
            while(node.getNext() != null && this.comp.compare(node.getKey(), key) != 0)
            {
				node = node.getNext();
			}

            if(this.comp.compare(node.getKey(), key) == 0)
            {
				return node;
			}
		}
		return null;
	}

    public boolean remove(K key)
    {

		int position = hash(key);

		HashNode target = (HashNode) hashTable[position];

		HashNode current = target;
		HashNode previous = null;

        while(current != null && current.getKey() != key)
        {
			previous = current;
			current = current.getNext();
		}

		if (current == null) return false;

        if (previous == null)
        {
			hashTable[position] = current.getNext();
		}else{
			previous.setNext(current.getNext());
		}

		nodesInTable--;
		return true;

	}

    
     // return an ArrayList of KeyVal pairs in the Hashmap
    public ArrayList<KeyValuePair<K,V>> entrySet()
    {

		ArrayList<KeyValuePair<K,V>> entrySet = new ArrayList<KeyValuePair<K,V>>();

        for(int i = 0; i < this.tableSize; i++)
        {
			HashNode node = (HashNode) hashTable[i];

            while (node != null)
            {
				entrySet.add(node.getData());
				node = node.getNext();
			}
		}

		return entrySet;
    }
    

    // Returns an ArrayList of all the keys in the Hashmap. There is no
    // defined order for the keys.
    public ArrayList<K> keySet()
    {
        ArrayList<K> keySet = new ArrayList<K>();

        for(int i = 0; i < this.tableSize; i++)
        {
			HashNode node = (HashNode) hashTable[i];

            while (node != null)
            {
				keySet.add(node.getKey());
				node = node.getNext();
			}
		}

		return keySet;
    }

    
    
    // Returns an ArrayList of all the values in the Hashmap. These should
    // be in the same order as the keySet.
    public ArrayList<V> values()
    {
        ArrayList<V> valueSet = new ArrayList<V>();

        for(int i = 0; i < this.tableSize; i++)
        {
			HashNode node = (HashNode) hashTable[i];

            while (node != null)
            {
				valueSet.add(node.getValue());
				node = node.getNext();
			}
		}

		return valueSet;
    }


     
    
    
    // Returns the number of key-value pairs in the Hashmap.
    //  public int size()
    //  {
    //     return this.nodesInTable;
    //  }

     
    public int size()
    {
        //if empty return size of 0
        if(this.nodesInTable == 0)
        {
            return 0;
        }
        //if not size is size of entryset Arraylist
        return this.entrySet().size();
    }
    
        
     
     
     
     
     // removes all mappings from this Hashmap and sets tthe size back to 128
     public void clear()
     {
        this.nodesInTable = 0;
        this.tableSize = 128;
        this.hashTable = new Object[this.tableSize];
     }


    public static void main(String[] args)
    {
        //create hash map with size 6
        Hashmap<String,String> testHashMap = new Hashmap<String,String>(new AscendingString(), 6 );
        
        System.out.println("Nodes in HashMap: " + testHashMap.size());
        System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        System.out.println("HashMap: " + testHashMap);
        // System.out.println("Entry Set: ");
        // for(KeyValuePair<String,String> kv : testHashMap.entrySet())
        // {
        //     System.out.println("entry set: " + kv);
        // }

		testHashMap.put("one", "ONE");
        testHashMap.put("two", "TWO");
        
        // System.out.println("Nodes in HashMap: " + testHashMap.size());
        // System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        // System.out.println("HashMap: " + testHashMap);
        
        testHashMap.put("three", "THREE");
        
        // System.out.println("Nodes in HashMap: " + testHashMap.size());
        // System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        // System.out.println("HashMap: " + testHashMap);
       
        
        testHashMap.put("four", "FOUR");

        
        testHashMap.put("five", "FIVE");
        testHashMap.put("five", "six");
        testHashMap.put("five", "seven");
        testHashMap.put("tree", "FIVE");

        System.out.println(testHashMap);
        System.out.println(testHashMap.entrySet());
        System.out.println(testHashMap.get("five"));
        System.out.println(testHashMap.get("one"));
        System.out.println(testHashMap.containsKey("one"));
        System.out.println(testHashMap.containsKey("onehundred"));
        // System.out.println("Max Size of HashMap Five as Five: " + testHashMap);
        // testHashMap.put("five", "thrtrh");
        // System.out.println("Max Size of HashMap Five as thrtrh: " + testHashMap);
        // //testHashMap.put("five", "FIVE");

        // System.out.println("Nodes in HashMap: " + testHashMap.size());
        // System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        // System.out.println("HashMap: " + testHashMap);
        // System.out.println("HashMap No Empties: " + testHashMap.toStringNoEmpties());

        // testHashMap.remove("two");

        // System.out.println("Nodes in HashMap: " + testHashMap.size());
        // System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        // System.out.println("HashMap: " + testHashMap);

        // System.out.println("Key Set: ");
        // for(String k : testHashMap.keySet())
        // {
        //     System.out.println("Key set: " + k);
        // }
        // System.out.println("value Set: ");
        // for(String v : testHashMap.values())
        // {
        //     System.out.println("value set: " + v);
        // }
        
        // testHashMap.clear();

        // System.out.println("Nodes in HashMap: " + testHashMap.size());
        // System.out.println("Max Size of HashMap: " + testHashMap.tableSize);
        // System.out.println("HashMap: " + testHashMap);
		

	}
    
}