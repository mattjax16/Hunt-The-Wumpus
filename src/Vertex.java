/*
***Matt Bass***
Vertex.java

This is the parent class for all rooms in the wampus graph

Fall 2020
CS 231 Project 9
*/

import java.util.ArrayList;
import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Vertex implements Comparable<Vertex>{





    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //Fields and Construtor for Vertex 



    //room neighbors positioned [north,south,east,west] if there is no connecting
    //Vertex spot is filled with null
    protected ArrayList<Vertex> neighborRooms;

    protected int xPos;

    protected int yPos;

    protected boolean isVisible;

    //in game of wampus is distance from wampus
    protected double distance;

    protected boolean visited;

    //parent of the vertex (room) on shortest path back
    protected Vertex parent;


    //compareable method using distance
    public int compareTo(Vertex v)
    {
        if(this.distance == v.getDistance())
        {
            return 0;
        }
        else if(this.distance > v.getDistance())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }

    
    
    
    //original empty constructor
    public Vertex()
    {
        new Vertex(0,0, false);
    }

    //original empty constructor
    public Vertex(int xPos, int yPos)
    {
        new Vertex(xPos, yPos, false);
    }


    //original empty constructor
    public Vertex(int xPos, int yPos, boolean visited)
    {
        this.neighborRooms = new ArrayList<Vertex>();
        this.neighborRooms.add(0, null);
        this.neighborRooms.add(1, null);
        this.neighborRooms.add(2, null);
        this.neighborRooms.add(3, null);
        this.xPos = xPos;
        this.yPos = yPos;
        this.isVisible = false;
        //is this a bodge making the double a max value to start with
        this.distance = Double.MAX_VALUE;
        this.visited = visited;
        this.parent = null;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //Getter and setters for Vertex 

    public void setAllNeighborRooms(ArrayList<Vertex> neighbors)
    {
        this.neighborRooms = neighbors;
    }


//    public void setANeighborRooms(Vertex neighbor, int direction)
//    {
//        if(direction <= 4)
//        {
//            this.neighborRooms.set(direction, neighbor);
//        }
//        else
//        {
//            this.neighborRooms.add(neighbor);
//        }
//    }

    public void setRandomNeighborRoom(Vertex neighbor){

    }


    public void setPosition(int x, int y)
    {
        this.xPos = x;
        this.yPos = y;
    }

    public void setXPos(int x)
    {
        this.xPos = x;
    }

    public void setYPos(int y)
    {
        this.yPos = y;
    }

    public void setVisible(boolean visible)
    {
        this.isVisible = visible;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public void setParent(Vertex parent)
    {
        this.parent = parent;
    }


    ///getters



    public ArrayList<Vertex> getNeighborRooms()
    {
        return this.neighborRooms;
    }

    public int[] getPosition()
    {
        int[] position = new int[2];

        position[0] = this.xPos;
        position[1] = this.yPos;
        
        return position;
    }


    public int getXPos()
    {
        return this.xPos;
    }


    public int getYPos()
    {
        return this.yPos;
    }

    public boolean getVisible()
    {
        return this.isVisible;
    }

    public double getDistance()
    {
        return this.distance;
    }

    public boolean getVisited()
    {
        return this.visited;
    }

    public Vertex getParent()
    {
        return this.parent;
    }


    public ArrayList<Vertex> getNeighbors() //returns an ArrayList of type Vertex which contains all of this Vertex' neighbors.
    {
        ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
        for(Vertex room : this.neighborRooms)
        {
            neighbors.add(room);
        }
        return neighbors;
    }




    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //Other methods and main test


    //returns the Euclidean distance between this vertex and the other vertex based on their x and y positions.
    public double distance( Vertex other )
    {

        int differenceInY = this.yPos - other.getYPos();
        int differenceInX = this.xPos - other.getXPos();


        double squareDist = (differenceInY) * (differenceInY) + (differenceInX) * (differenceInX);
        double finalDistance = Math.sqrt(squareDist);
        return finalDistance;
    }



    //updates this vertex' adjacency list/map so that it connects with the other Vertex. This is a uni-directional link.
    public void connect(Vertex neighbor)
    {
        
        int direction = directionOf(neighbor);
        
        if(direction <= 4)
        {
            this.neighborRooms.set(direction, neighbor);
        }
        else
        {
            this.neighborRooms.add(neighbor);
        }
    }

    //returns the Vertex at position (x, y) if the Vertex is in the adjacency list, otherwise null.
    public Vertex getNeighbor(int x, int y)
    {
        for(Vertex neighbor : this.neighborRooms)
        {
            if(neighbor != null)
            {
                if((neighbor.getXPos() == x) && (neighbor.getYPos() == y))
                {
                    return neighbor;
                }
            }

        }
        return null;
    }



    public int numNeighbors() //returns the number of connected vertices.
    {
        int numNeighbors = 0;

        for(Vertex neighbor : this.neighborRooms)
        {
            if(neighbor != null)
            {
               numNeighbors++;
            }    
        }
        return numNeighbors;
    }



    public String toString() //returns a String containing (at least) the number of neighbors, this Vertex' cost (distance from whomp), and the marked flag.
    {
        return ("[ " + numNeighbors() + ",  " + getDistance()  + ",  {" + getXPos() + " , "+ getYPos() + "} ]");
    }

    
    //returns true if the x and y positions of the two vertices match.
    public static boolean matchPosition( Vertex a, Vertex b )
    {
        if(a.getPosition() == b.getPosition())
        {
            return true;
        }
        return false;
    }




    //method to get direction of one vertex compared to the other if null they arent directly next to each other
    public int directionOf(Vertex vert)
    {
        int otherX = vert.getXPos();
        int otherY = vert.getYPos();

        if(otherX == this.xPos)
        {
            if(this.yPos - otherY == -1)
            {
                return 1;
            }
            if(this.yPos - otherY == 1)
            {
                return 0;
            }
        }

        if(otherY == this.yPos)
        {
            if(this.xPos - otherX == -1)
            {
                return 2;
            }
            if(this.xPos - otherX == 1)
            {
                return 3;
            }
        }
        return 5;
    }



    //draw method with a scale of 64
    public void draw(Graphics g)
    {
        int scale = 64;
        if (!this.isVisible)
            return;
        int xpos = (int)this.getXPos()*scale;
        int ypos = (int)this.getYPos()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        // draw rectangle for the walls of the room
        if (this.getDistance() <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);

        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);

        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNeighbor( this.getXPos(), this.getYPos()-1 ) != null )
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.getNeighbor( this.getXPos(), this.getYPos()+1 ) != null )
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),
                    eighth, eighth + sixteenth);
        if (this.getNeighbor( this.getXPos()-1, this.getYPos()) != null)
        g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getNeighbor( this.getXPos()+1, this.getYPos())!= null)
        g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,
                eighth + sixteenth, eighth);
    }


    public void draw(Graphics g, int scale)
    {
        if (!this.isVisible)
            return;
        int xpos = (int)this.getXPos()*scale;
        int ypos = (int)this.getYPos()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        // draw rectangle for the walls of the room
        if (this.getDistance() <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);

        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);

        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNeighbor( this.getXPos(), this.getYPos()-1 ) != null )
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.getNeighbor( this.getXPos(), this.getYPos()+1 ) != null )
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),
                    eighth, eighth + sixteenth);
        if (this.getNeighbor( this.getXPos()-1, this.getYPos() ) != null)
        g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getNeighbor( this.getXPos()+1, this.getYPos() )!= null)
        g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,
                eighth + sixteenth, eighth);
    }




}