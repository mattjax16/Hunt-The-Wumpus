
/*
***Matt Bass***
TwoWayRoom.java

This is a room in the cave that has doors to all other caves

Fall 2020
CS 231 Project 9
*/

public class TwoWayRoom extends Room {


    public TwoWayRoom(int yPos, int xPos, boolean visited, Vertex room1,Vertex room2)
    {
        super(yPos, xPos, visited);
        this.neighborRooms.add(directionOf(room1), room1);
        this.neighborRooms.add(directionOf(room2), room2);
    }


    //TODO Over write the draw method with am image.
}





