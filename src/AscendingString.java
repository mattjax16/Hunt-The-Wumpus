 /**Matt Bass Project 8 AscendingString.java
 * this is an comparator object used for comparing strings (not sure wether to use T or string) by alphabetical order (lexicographical)
 */

 import java.util.Comparator;

//this is a comparator of type string
 public class AscendingString implements Comparator<String>{

        //comparator method that compares string
        public int compare(String string1 , String string2)
        {
            //QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION
            //QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION QUESTION
            //can i Just use ignore case instead of converting both to lower case 
            return string1.compareTo(string2);
        }



        //---------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------
        //main test method
        public static void main(String[] args)
        {
            //create new compare object
            AscendingString testComp = new AscendingString();

            //should be greater than 0 because tree is higher alphabetical than builder
            System.out.println(testComp.compare("Tree", "Builder"));
            //should be 0 because tree is tree
            System.out.println(testComp.compare("Tree", "Tree"));
             //should be less than 0 because builder is higher alphabetical than tree
             System.out.println(testComp.compare("builder","tree"));
             //should be 0 because tree is tree
            System.out.println(testComp.compare("Tree,", "Tree"));

        }
 }  