/**Matt Bass Project 9 ClosestVertex.java
 * this is an comparator object used for comparing vertexes by their distance
 */

import java.util.Comparator;

//this is a comparator of type Vertex
 public class ClosestVertex implements Comparator<Vertex>{

        //comparator method that compares string
        public int compare(Vertex v1 , Vertex v2)
        {
            if(v1.getDistance() == v2.getDistance())
            {
                return 0;
            }
            else if(v1.getDistance() < v2.getDistance())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }