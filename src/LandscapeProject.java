
/**Matt Bass Project9 LandscapeProject.java
 *
 */

import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;
import java.util.LinkedList;

public class LandscapeProject{

    //fields for the width and height (ints) (for the purposes of drawing)
    private int width;
    private int height;

    //an ArrayList<Vertex> to hold the list of all the rooms in the game of wampus
    private LinkedList<Vertex> roomList;

    private Hunter hunter;
    private Wumpus wupus;


    //int w, int h, LinkedList<Vertex> roomList, Hunter hunter, Wumpus wupus
    //constructor.\
    public LandscapeProject(int w, int h, Hunter hunter, Wumpus wupus)
    {
        this.width = w;
        this.height = h;
        this.roomList = new LinkedList<Vertex>();
        this.hunter = hunter;
        this.wupus = wupus;
    }

    public LandscapeProject(int w, int h)
    {
        this.width = w;
        this.height = h;
        this.roomList = new LinkedList<Vertex>();
        this.hunter = null;
        this.wupus = null;
    }

    public void addHunter(Hunter hunter)
    {
        this.hunter = hunter;
    }

    public void addWumpus(Wumpus wumpus)
    {
        this.wupus = wumpus;
    }



    //return the height of the Landscape.
    public int getHeight()
    {
        return this.height;
    }


    //return the roomlist of the Landscape.
    public LinkedList<Vertex> getRoomList()
    {
        return this.roomList;
    }

    //return the width of the Landscape.
    public int getWidth()
    {
        return this.width;
    }


    //return a string indicating how many vertices there are
    public String toString()
    {
        return ("Number of Vertices: " + this.roomList.size() );
    }



    //loop through the vertices first and draw them, then draw the Hunter and Wumpus.
    public void draw( Graphics g )
    {
        g = (Graphics2D)g;
        g.setColor(Color.cyan);
        for(Vertex room: this.roomList)
        {
            room.draw(g);
        }
        if(this.hunter != null)
            this.hunter.draw(g);
        if(this.wupus != null)
            this.wupus.draw(g);
    }




    //loop through the vertices first and draw them, then draw the Hunter and Wumpus.
    //take in a scale
    public void draw( Graphics g, int scale)
    {
        for(Vertex room: this.roomList)
        {
            room.draw(g, scale);
        }
        if(this.hunter != null)
            this.hunter.draw(g);
        if(this.wupus != null)
            this.wupus.draw(g);
    }


    public void addBackgroundAgent(Vertex v1)
    {
        this.roomList.add(v1);
    }
}


