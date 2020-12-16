 /**Matt Bass Project 8 KeyValuePair.java
 * the object in the value (data) of the node in the BST bianiary search tree
 * in this case for Project 6 key holds the word and value is the number of times the word appears
 * 
 */

public class KeyValuePair<Key,Value> 
{
        //intializing private fields for generaic types Key and Value
        Key key;
        Value value;


        //the constructor initializing the key and value fields.
        public KeyValuePair( Key k, Value v )
        {
            this.key = k;
            this.value = v;
        }



//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//GETTERS

        //returns the key (this.key)
        public Key getKey()
        {
            return this.key;
        }


        //returns the value (this.value)
        public Value getValue()
        {
            return this.value;
        }




//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//SETTERS


        //sets the key.(this.key)
        public void setKey( Key k )
        {
            this.key = k;
        }


        //sets the value.(this.value)
        public void setValue( Value v )
        {
            this.value = v;
        }


//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------------
//To String and Main test methods


        //returns a String containing both the key and value.
        public String toString()
        {
            //question use gettor or just call this.
            return ("< " + this.getKey() + " , " + this.getValue() + " >");
            //changed to string to more fit the project
            //return(this.getKey() + " " + this.getValue());
        }


        //mainmethod to test
        public static void main(String[] args)
        {
            //create a new Key Value with a word him as the key and number 10 as value
            KeyValuePair< String ,Integer > testKVstringINT = new KeyValuePair<String,Integer>("him", 10);
            //print out then KeyValuePair< String ,Integer > testKVstringINT testing toString method
            System.out.println(testKVstringINT);

            //test changing the value and print it out
            testKVstringINT.setValue(232);
            System.out.println(testKVstringINT);

            //test changing the key and print it out
            testKVstringINT.setKey("HER");
            System.out.println(testKVstringINT);

        }
}