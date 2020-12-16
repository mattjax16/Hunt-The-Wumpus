/**Matt Bass Project9 Landscape.java
 *
 * MKodified this to add a graph
 */

import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;
import java.util.LinkedList;

public class Landscape{

        //fields for the width and height (ints) (for the purposes of drawing)
        private int width;
        private int height;

        //an LinkedList<Vertex> to hold the list of all the rooms in the game of wampus
        private LinkedList<Vertex> roomList;

        //an LinkedList<Arrow> to hold the list of all the arrows in the game of wampus
        private LinkedList<Arrow> arrowList;

        private Hunter hunter;
        private Wumpus wupus;

        private LinkedList<Trap> trapList = new LinkedList<Trap>();

        private LinkedList<Bat> batList = new LinkedList<Bat>();


        //int w, int h, LinkedList<Vertex> roomList, Hunter hunter, Wumpus wupus
        //constructor.\
        public Landscape(int w, int h, Hunter hunter, Wumpus wupus)
        {
            this.width = w;
            this.height = h;
            this.roomList = new LinkedList<Vertex>();
            this.hunter = hunter;
            this.wupus = wupus;
            this.arrowList = new LinkedList<Arrow>();

        }

        public Landscape(int w, int h)
        {
            this.width = w;
            this.height = h;
            this.roomList = new LinkedList<Vertex>();
            this.hunter = null;
            this.wupus = null;
            this.arrowList = new LinkedList<Arrow>();
        }

        public void addHunter(Hunter hunter)
        {
            this.hunter = hunter;
        }

        public void addWumpus(Wumpus wumpus)
    {
        this.wupus = wumpus;
    }

        public void addArrow(Arrow arrow)
        {
            this.arrowList.add(arrow);
        }

        public void addTrap(Trap trap)
        {
            this.trapList.add(trap);
        }

        public void addBat(Bat bat)
        {
            this.batList.add(bat);
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
    //take in a scale
    public void draw( Graphics g, int scale)
    {
        for(Vertex room: this.roomList)
        {
            room.draw(g, scale);
        }

        if(this.wupus != null)
            this.wupus.draw(g);
        if(!arrowList.isEmpty())
        {
            for(Arrow arrow : this.arrowList)
            {
                arrow.draw(g);
            }
        }
        if(!this.trapList.isEmpty())
        {
            for(Trap trap: this.trapList)
            {
                trap.draw(g);
            }
        }

        if(!this.batList.isEmpty())
        {
            for(Bat bat: this.batList)
            {
                bat.draw(g);
            }
        }

        if(this.hunter != null)
            this.hunter.draw(g);
    }


    public void addBackgroundAgent(Vertex v1)
    {
        this.roomList.add(v1);
    }
}