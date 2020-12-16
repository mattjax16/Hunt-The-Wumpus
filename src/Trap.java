
/*
***Matt Bass***
Wumpus.java

This is the Wumpus (target) object in the game

Fall 2020
CS 231 Project 9
*/

import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;
import java.awt.*;





public class Trap {

    private Vertex currentRoom;


    public Trap(Vertex room)
    {
        this.currentRoom = room;
    }



    //draw with default scale of 64
    public void draw(Graphics g)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(204, 97, 250));

            int scale = 64;
            int xpos = (int)this.currentRoom.getXPos()*scale;
            int ypos = (int)this.currentRoom.getYPos()*scale;
            int border = 2;
            int half = scale / 2;
            int eighth = scale / 8;
            int sixteenth = scale / 16;
            int startX = (xpos - 5);
            int startY = (ypos - 5);
            Image trapImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/trap5.png").getImage();
            g.drawImage(trapImg, startX, startY, null);
    }


    //method to get what room it is in
    public Vertex getRoom()
    {
        return this.currentRoom;
    }


}



