
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

import java.util.*;




public class Arrow {

    private Vertex currentRoom;
    private int damage = 1;
    private boolean visible;

    public Arrow(Vertex room)
    {
        this.currentRoom = room;
        this.visible = false;
    }

    public Arrow()
    {
        this.currentRoom = null;
        this.visible = false;
    }


    public void draw(Graphics g, int scale)
    {

        if(this.visible)
        {
            Random ran = new Random();
            int xpos = (int)this.currentRoom.getXPos()*scale;
            int ypos = (int)this.currentRoom.getYPos()*scale;
            int border = 2;
            int half = scale / 2;
            int eighth = scale / 8;
            int sixteenth = scale / 16;
            int startX = (xpos + border)+half-ran.nextInt(eighth);
            int startY = (ypos + border)+half-ran.nextInt(eighth);
            Image arrowImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/arrowE.png").getImage();
            g.drawImage(arrowImg, startX, startY, null);
        }

    }




    //draw with default scale of 64
    public void draw(Graphics g)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(204, 97, 250));

        if(this.visible)
        {

            int scale = 64;
            int xpos = (int)this.currentRoom.getXPos()*scale;
            int ypos = (int)this.currentRoom.getYPos()*scale;
            int border = 2;
            int half = scale / 2;
            int eighth = scale / 8;
            int sixteenth = scale / 16;
            int startX = (xpos + border)+half-eighth;
            int startY = (ypos + border)+half-eighth;
            Image arrowImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/arrowE.png").getImage();
            g.drawImage(arrowImg, startX, startY, null);
        }
    }


    public int getDamage()
    {
        return this.damage;
    }

    public void setDamage(int dmg)
    {
        this.damage = dmg;
    }


    //method to get what room it is in
    public Vertex getRoom()
    {
        return this.currentRoom;
    }





    public void shoot(Vertex room)
    {
        this.visible = true;
        this.currentRoom = room;
    }


    //same thing as shoot just different name and doesnt make visable
    public void move(Vertex room)
    {
        this.currentRoom = room;
    }
}


