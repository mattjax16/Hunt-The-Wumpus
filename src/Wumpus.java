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





public class Wumpus {

    private Vertex currentRoom;
    private int health;
    private boolean alive = true;
    private boolean isVisable = false;

    public Wumpus(Vertex room)
    {
        this.currentRoom = room;
        this.health = 1;
    }
    public Wumpus(Vertex room, int health)
    {
        super();
        this.health = health;
    }

    public void setIsVisable(boolean visable)
    {
        this.isVisable = visable;
    }
    public void draw(Graphics g, int scale)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(156, 90, 16));

        if(this.isVisable)
        {
            int xpos = (int)this.currentRoom.getXPos()*scale;
            int ypos = (int)this.currentRoom.getYPos()*scale;
            int border = 2;
            int half = scale / 2;
            int eighth = scale / 8;
            int sixteenth = scale / 16;
            int startX = (xpos + border)+(eighth*3);
            int startY = (ypos + border)+(eighth*3);
            Image wumpImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/wumpus.png").getImage();
            g.drawImage(wumpImg, startX, startY, null);
        }
    }


    //draw with default scale of 64
    public void draw(Graphics g)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(204, 97, 250));

       if(this.isVisable)
       {
           int scale = 64;
           int xpos = (int)this.currentRoom.getXPos()*scale;
           int ypos = (int)this.currentRoom.getYPos()*scale;
           int border = 2;
           int half = scale / 2;
           int eighth = scale / 8;
           int sixteenth = scale / 16;
           int startX = (xpos + border)+(eighth*2);
           int startY = (ypos + border)+(eighth*2);
           Image wumpImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/wumpus.png").getImage();
           g.drawImage(wumpImg, startX, startY, null);
       }
    }


    //method to get what room it is in
    public Vertex getRoom()
    {
        return this.currentRoom;
    }

    public boolean isLiving()
    {
        return this.alive;
    }


    //method to shoot the wumpus
    public void shot(Arrow arrow)
    {
        this.health = this.health - arrow.getDamage();
        updateALive();
    }

    //helper method to update if it alive
    public void updateALive()
    {
        if(this.health <= 0)
        {
            this.alive = false;
        }
    }

}
