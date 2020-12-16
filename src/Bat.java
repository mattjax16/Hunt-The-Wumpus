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
import java.util.Random;


public class Bat {

    private Vertex currentRoom;
    private int health = 50;
    private int damage = 1;
    private boolean alive = true;
    private boolean isVisable = false;
    private int flap = 0;

    public Bat(Vertex room)
    {
        this.currentRoom = room;
    }



    public void setIsVisable(boolean visable)
    {
        this.isVisable = visable;
    }


    public int getDamage()
    {
        return this.damage;
    }

    //draw with default scale of 64
    public void draw(Graphics g)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(204, 97, 250));

        if(this.isVisable)
        {
            this.flap++;
            int scale = 64;
            int xpos = (int)this.currentRoom.getXPos()*scale;
            int ypos = (int)this.currentRoom.getYPos()*scale;
            int border = 2;
            int half = scale / 2;
            int eighth = scale / 8;
            int sixteenth = scale / 16;
            int startX = (xpos + border + 5)+(eighth*2);
            int startY = (ypos + border + 5)+(eighth*2);


            Image batImg = null;

            //make it flap
            if(this.flap % 2 == 0)
            {
                batImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/batDown.png").getImage();
            }
            else
            {
                batImg = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/batUP.png").getImage();
            }

            Random ran = new Random();

            g.drawImage(batImg, startX + ran.nextInt(5), startY + ran.nextInt(5), null);
        }
    }


    //method to get what room it is in
    public Vertex getRoom()
    {
        return this.currentRoom;
    }





}






