/*
***Matt Bass***
Hunter.java

This is the hunter (player) object in the game

Fall 2020
CS 231 Project 9
*/

import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;
import java.awt.*;





public class Hunter {

    private Vertex currentRoom;

    private int health = 100;
    private boolean alive = true;

    //quiver to hold arrows at most 6
    private ArrayList<Arrow> quiver = new ArrayList<Arrow>();

    public Hunter(Vertex room)
    {
        this.currentRoom = room;
        this.currentRoom.setVisible(true);
    }



    public void draw(Graphics g, int scale)
    {
        //make the color of the hunter brown

        //g.setColor(new Color(156, 90, 16));

        int xpos = (int)this.currentRoom.getXPos()*scale;
        int ypos = (int)this.currentRoom.getYPos()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        int startX = (xpos + border)+half-eighth;
        int startY = (ypos + border)+half-eighth;
        Image hunterImage = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/hunter.png").getImage();

    }


    //draw with default scale of 64
    public void draw(Graphics g)
    {
        //make the color of the hunter brown

//        g.setColor(new Color(156, 90, 16));

        int scale = 64;
        int xpos = (int)this.currentRoom.getXPos()*scale;
        int ypos = (int)this.currentRoom.getYPos()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        int startX = (xpos + border)+half-eighth;
        int startY = (ypos + border)+half-eighth;
        Image hunterImage = new ImageIcon("/Users/matthewbass/Desktop/school/colby/cs231/proj09cs231/womp1/src/gameImages/hunter.png").getImage();
        g.drawImage(hunterImage, startX, startY, null);

    }





    //add a function to let the hunter move if the hunter can sucsefully move that
    //that direction NSEW based on 0123 it returns true if not false
    public boolean move(int direction)
    {
        if(this.currentRoom.neighborRooms.get(direction) != null)
        {
            this.currentRoom = this.currentRoom.neighborRooms.get(direction);
            for(Arrow arrow: this.quiver)
            {
                arrow.move(this.currentRoom);
            }
            this.currentRoom.setVisible(true);
            return true;
        }
        return false;
    }


    //method to add arrows
    public void addArrow(Arrow arrow)
    {
        arrow.move(this.currentRoom);
        this.quiver.add(arrow);

    }


    //add a function to let the hunter shoot if the hunter can sucsefully move that
    //that direction NSEW based on 0123 it returns true if not false
    public Arrow shoot(int direction)
    {
        if(this.quiver.size() != 0)
        {
         System.out.println("Arrow shot Direction " + direction);

            Vertex targetRoom = this.currentRoom.neighborRooms.get(direction);
            if(targetRoom != null)
            {
                Arrow shot = this.quiver.remove(0);

                shot.shoot(targetRoom);



                System.out.println("Arrow shot. You have " + this.quiver.size() + " arrows remaining.");
                return shot;
            }
            else
            {
                System.out.println("Cant shoot Arrow in that Direction");
                return null;
            }

        }
        System.out.println("Out of Arrows");
        return null;
    }




    public boolean isAlive()
    {
        return this.alive;
    }

    public void inflictDamage(int damage)
    {
        this.health = this.health - damage;
        setAlive();
    }


    public void setAlive()
    {
        if(this.health <= 0)
        {
            this.alive = false;
        }
    }


    //method to get what room it is in
    public Vertex getRoom()
    {
        return this.currentRoom;
    }

}
