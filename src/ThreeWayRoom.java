

/*
***Matt Bass***
ThreeWayRoom.java

This is a room in the cave that has doors to all other caves

Fall 2020
CS 231 Project 9
*/

public class ThreeWayRoom extends Room {


    public ThreeWayRoom(int yPos, int xPos, boolean visited, Vertex room1,Vertex room2,Vertex room3)
    {
        super(yPos, xPos, visited);
        this.neighborRooms.add(directionOf(room1), room1);
        this.neighborRooms.add(directionOf(room2), room2);
        this.neighborRooms.add(directionOf(room3), room3);

    }


    //TODO Over write the draw method with am image.
}




