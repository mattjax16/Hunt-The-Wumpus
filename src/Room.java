
//parennt class for all rooms in the game


import javax.swing.*;
import java.awt.*;

public class Room  extends Vertex{

    protected Image roomImg;

    public Room(int xPos, int yPos, boolean visited)
    {
        super(xPos, yPos, visited);
    }

    @Override
    public void connect(Vertex neighbor)
    {

        int direction = directionOf(neighbor);

        if(direction <= 4)
        {
            this.neighborRooms.set(direction, neighbor);
        }
    }

    @Override
    public void draw(Graphics g)
    {


        if(this.isVisible)
        {
            String basename = "/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase";

            //there is only one case here of the base room if it has 4 eneighbors
            if(this.numNeighbors() == 4)
            {
                this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase.png").getImage();
            }




            //case where there are 3 entrances to the room
            else if(this.numNeighbors() == 3)
            {

                //check for which room of 3 way rooms it is


                //if there is no north connection room most be SEW (south east west) that is how labeling will
                //work for now on in this function and when referencing to rooms
                if(this.neighborRooms.get(0) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3SEW.png").getImage();
                }
                //if there is no south neighbour
                else if(this.neighborRooms.get(1) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NEW.png").getImage();
                }//if there is no east neighbour
                else if(this.neighborRooms.get(2) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NSW.png").getImage();
                }//if there is no west neighbour
                else if(this.neighborRooms.get(3) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NSE.png").getImage();
                }

            }

            //case where there are 2 entrances to the room
            else if(this.numNeighbors() == 2) {
                //check for which 2 way room it is

                //if there is no north neighbour
                if (this.neighborRooms.get(0) == null) {
                    //if there is no south neighbour
                    //it is a EW connection
                    if (this.neighborRooms.get(1) == null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2EW.png").getImage();
                    }

                    //if there is no east neighbour
                    //it is a SW connection
                    else if (this.neighborRooms.get(2) == null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2SW.png").getImage();
                    }

                    //if there is no west neighbour
                    //it is a SE connection
                    else {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2SE.png").getImage();
                    }

                }

                //if there is a north connecting neighbor
                else {
                    //if there is a south neighbour
                    //it is a NS connection
                    if (this.neighborRooms.get(1) != null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NS.png").getImage();
                    }

                    //if there is a east neighbour
                    //it is a NE connection
                    else if (this.neighborRooms.get(2) != null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NE.png").getImage();
                    }

                    //if there is a west neighbour
                    //it is a NW connection
                    else {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NW.png").getImage();
                    }
                }
            }
            //case where there are 1 entrance to the room
            else if(this.numNeighbors() == 1)
            {
                //if there is only a north connection
                if(this.neighborRooms.get(0) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1N.png").getImage();
                }
                //iof there is only a south connection
                else if(this.neighborRooms.get(1) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1S.png").getImage();
                }

                //if there is only a east neighbour
                else if(this.neighborRooms.get(2) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1E.png").getImage();
                }

                //if there is a west neighbour
                //it is a W connection
                else
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1W.png").getImage();
                }
            }
            //this.roomImg = new ImageIcon(basename+"3NSE.png").getImage();
            g.drawImage(roomImg, this.xPos, this.yPos, null);
        }
    }





    public void draw(Graphics g, int scale)
    {

        if(this.isVisible)
        {
            String basename = "/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase";

            //there is only one case here of the base room if it has 4 eneighbors
            if(this.numNeighbors() == 4)
            {
                this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase.png").getImage();
            }




            //case where there are 3 entrances to the room
            else if(this.numNeighbors() == 3)
            {

                //check for which room of 3 way rooms it is


                //if there is no north connection room most be SEW (south east west) that is how labeling will
                //work for now on in this function and when referencing to rooms
                if(this.neighborRooms.get(0) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3SEW.png").getImage();
                }
                //if there is no south neighbour
                else if(this.neighborRooms.get(1) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NEW.png").getImage();
                }//if there is no east neighbour
                else if(this.neighborRooms.get(2) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NSW.png").getImage();
                }//if there is no west neighbour
                else if(this.neighborRooms.get(3) == null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase3NSE.png").getImage();
                }

            }

            //case where there are 2 entrances to the room
            else if(this.numNeighbors() == 2) {
                //check for which 2 way room it is

                //if there is no north neighbour
                if (this.neighborRooms.get(0) == null) {
                    //if there is no south neighbour
                    //it is a EW connection
                    if (this.neighborRooms.get(1) == null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2EW.png").getImage();
                    }

                    //if there is no east neighbour
                    //it is a SW connection
                    else if (this.neighborRooms.get(2) == null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2SW.png").getImage();
                    }

                    //if there is no west neighbour
                    //it is a SE connection
                    else {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2SE.png").getImage();
                    }

                }

                //if there is a north connecting neighbor
                else {
                    //if there is a south neighbour
                    //it is a NS connection
                    if (this.neighborRooms.get(1) != null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NS.png").getImage();
                    }

                    //if there is a east neighbour
                    //it is a NE connection
                    else if (this.neighborRooms.get(2) != null) {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NE.png").getImage();
                    }

                    //if there is a west neighbour
                    //it is a NW connection
                    else {
                        this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase2NW.png").getImage();
                    }
                }
            }
            //case where there are 1 entrance to the room
            else if(this.numNeighbors() == 1)
            {
                //if there is only a north connection
                if(this.neighborRooms.get(0) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1N.png").getImage();
                }
                //iof there is only a south connection
                else if(this.neighborRooms.get(1) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1S.png").getImage();
                }

                //if there is only a east neighbour
                else if(this.neighborRooms.get(2) != null)
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1E.png").getImage();
                }

                //if there is a west neighbour
                //it is a W connection
                else
                {
                    this.roomImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/roombase1W.png").getImage();
                }
            }
            //this.roomImg = new ImageIcon(basename+"3NSE.png").getImage();
            g.drawImage(roomImg, this.xPos*scale, this.yPos*scale, null);
            if(this.distance <= 2)
            {
                Blood blood = new Blood(this);
                blood.draw(g);
            }
        }

    }

//didnt need alreadt made
//    //helper method fore draw to help decide what kind of room should be evaluated
//    public int numNeighbors()
//    {
//        int numNeighbor = 0;
//        for(Vertex room: this.neighborRooms)
//        {
//            if(room != null)
//            {
//                numNeighbor++;
//            }
//        }
//        return numNeighbor;
//    }

}
